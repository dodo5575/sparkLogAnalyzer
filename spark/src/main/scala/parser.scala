// A helper function to parse the lines in the log file.
package parser

// for regular expression
import scala.util.matching.Regex

// The logData class to store all of the data extracted from the log file.
case class logData(ipAddress: String,
  dateTime: String,
  method: String,
  endpoint: String,
  protocol: String,
  responseCode: Int,
  contentSize: Long) {
}

object parser {

    // This is the regular expression pattern to extract data
    val PATTERN = new Regex("(.*) - - \\[(.*)\\]\\s+\"(.*) (.*) (.*)\" (.*) (.*)")
    def parse_log_line(logLine: String): logData = {
        /** 
        input: each line of the log file as String.
        output: logData object holding the extracted data
        **/

        logLine match {
            case PATTERN(ipAddress, dateTime, method, endpoint, protocol, responseCode, contentSize)
                => logData(ipAddress, dateTime, method, endpoint, protocol, responseCode.toInt, contentSize.toLong)

        }

    }

}


