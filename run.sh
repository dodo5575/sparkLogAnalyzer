# compile
cd spark
sbt package

# run
cd ../
spark-submit spark/target/scala-2.11/log-analyzer_2.11-1.0.jar data/apache.access.log output/contentSize output/response

