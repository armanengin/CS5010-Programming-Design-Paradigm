package assignment5.concurrentSolution;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The main class for the application that processes course and student activity data. This class
 * initializes producer and consumer threads for reading course and student activity data from CSV
 * files, processes them, and then generates summary and high activity files based on a specified
 * activity threshold.
 * <p>
 * The course and student activity data are read from predefined CSV file paths. The data is
 * processed concurrently using separate threads for producers (reading data) and consumers
 * (processing data). The summary and high activity data are then written to files in a specified
 * output directory.
 */
public class Main {

  private static final String NOT_ENOUGH_ARGUMENTS_ERROR = "Please provide the path to the directory containing the OULAD CSV files and the activity threshold.";
  private static final String COURSE_FILENAME = "courses.csv";
  private static final String STUDENT_ACTIVITY_FILENAME = "StudentVle.csv";
  private static final String OUTPUT_DIRECTORY = "out";
  private static final int DEFAULT_THRESHOLD = 0;
  private static final int DEFAULT_NUM_THREADS = 4;
  private static final String THRESHOLD_PARSE_ERROR = "Threshold must be an integer.";
  private static final int ERROR_EXIT_CODE = 1;

  /**
   * The main method that drives the application. It sets up producer and consumer threads for
   * courses and student activities, waits for all threads to complete, and then generates summary
   * and high activity output files.
   *
   * @param args Command-line arguments passed to the program. The first argument, if present,
   *             specifies the activity threshold for identifying high activity days. If no argument
   *             is provided, a default threshold value is used.
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println(NOT_ENOUGH_ARGUMENTS_ERROR);
      return;
    }

    String courseCsvFilePath = args[0] + File.separator + COURSE_FILENAME;
    String studentActivityCsvFilePath = args[0] + File.separator + STUDENT_ACTIVITY_FILENAME;

    int threshold = DEFAULT_THRESHOLD;
    try {
      threshold = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      System.err.println(THRESHOLD_PARSE_ERROR);
      System.exit(ERROR_EXIT_CODE);
    }

    BlockingQueue<List<String>> courseQueue = new LinkedBlockingQueue<>();
    BlockingQueue<List<String>> studentActivityQueue = new LinkedBlockingQueue<>();
    ConcurrentHashMap<String, Map<Integer, Integer>> summaryDataMap = new ConcurrentHashMap<>();
    List<List<String>> highActivityMap = new ArrayList<>();

    List<Thread> producerThreads = new ArrayList<>();
    List<Thread> consumerThreads = new ArrayList<>();

    Thread courseProducer = new Thread(
        new CourseProducer(courseCsvFilePath, courseQueue));
    courseProducer.start();
    producerThreads.add(courseProducer);

    Thread studentActivityProducer = new Thread(
        new StudentActivityProducer(studentActivityCsvFilePath, studentActivityQueue));
    studentActivityProducer.start();
    producerThreads.add(studentActivityProducer);

    for (int i = 0; i < DEFAULT_NUM_THREADS; i++) {
      Thread courseConsumer = new Thread(
          new CourseConsumer(courseQueue, summaryDataMap));
      courseConsumer.start();
      consumerThreads.add(courseConsumer);
    }

    for (int i = 0; i < DEFAULT_NUM_THREADS; i++) {
      Thread studentActivityConsumer = new Thread(
          new StudentActivityConsumer(studentActivityQueue, summaryDataMap));
      studentActivityConsumer.start();
      consumerThreads.add(studentActivityConsumer);
    }

    for (Thread thread : producerThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    for (Thread thread : consumerThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    OULADFileProcessor.generateSummaryFiles(summaryDataMap, OUTPUT_DIRECTORY, highActivityMap,
        threshold);
    OULADFileProcessor.generateHighActivityFile(highActivityMap, OUTPUT_DIRECTORY, threshold);
  }
}
