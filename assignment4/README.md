- To **build** .jar file, run the following command in the root directory of the project:

    ```
    ./gradlew jar
    ```

  The .jar file will be located in the `build/libs` directory.


- To **run** the .jar file, run the following command in the root directory of the project:

    ```
    java -jar ./build/libs/assignment4-1.0.jar <<grammar directory path>
    
    example:
    java -jar ./build/libs/assignment4-1.0.jar ./build/resources/main/
    ```

- To **test** the project, run the following command in the root directory of the project:

    ```
    ./gradlew doAll
    ``` 

- Custom grammar files can be added to the `src/main/resources` directory. The grammar file should
  be a .json file with the following format:

    ```
    {
        "grammarTitle": "title",
        "grammarDesc": "description", -- optional
        "start": "terminal <non-terminal>",
        "non-terminal": [
            "terminal <non-terminal>",
            ...
        ],
        ...
    }
    ```