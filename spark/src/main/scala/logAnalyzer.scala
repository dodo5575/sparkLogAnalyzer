// spark related
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._

// for the customized parser function
import parser._

object logAnalyzer {

    def main(args: Array[String]) {

        // read logfile path and output directories from stdin
        val logFile = args(0)
        val contentSizeOut = args(1)
        val responseCodeOut = args(2)


        val spark = SparkSession.builder.appName("Log Analyzer").getOrCreate()
        import spark.implicits._

        /**
        1. read the logfile line by line
        2. map the parser function to each line, which will return logData object
        3. convert the logData object to spark dataFrame
        **/
        var logDataDF = spark.sparkContext
                             .textFile(logFile)
                             .map(parser.parse_log_line)
                             .toDF()


        // extract the average content size and save it as CSV file
        logDataDF.select(avg("contentSize"))
                 .write
                 .format("csv")
                 .option("header", "true")
                 .save(contentSizeOut)


        // count the occurance of responseCode,
        // calculate the ratio and save the data as CSV file
        logDataDF.select("responseCode")
                 .groupBy("responseCode")
                 .agg(count("responseCode").alias("count"))
                 .withColumn("fraction", col("count") / sum("count").over())
                 .write
                 .format("csv")
                 .option("header", "true")
                 .save(responseCodeOut)

    }
}


