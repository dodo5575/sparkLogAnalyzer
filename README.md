# sparkLogAnalyzer
Simple log analyzer using Scala and Spark

## What it does:
Parse a log file and
1. calculate the average content size
2. count the occurance of HTTP status code and calculate the ratio 
using spark dataFrame.


## Major components in the repository
```
├── README.md
├── data     ## input
├── output   ## output
├── run.sh   
└── spark   
    ├── build.sbt
    └── src
        └── main
            └── scala  ## contains all of the code files
                ├── logAnalyzer.scala
                └── parser.scala
```
You can run it using `run.sh`.
