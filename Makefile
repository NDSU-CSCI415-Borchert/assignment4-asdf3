HADOOP_CLASSPATH=$(shell hadoop classpath)

all: MostPopularPath BiggestUser

MostPopularPath: MostPopularPath.java MostPopularPathMapper.java MostPopularPathReducer.java
	mkdir MostPopularPath
	javac -classpath $(HADOOP_CLASSPATH) -d MostPopularPath/ MostPopularPath.java MostPopularPathReducer.java MostPopularPathMapper.java
	jar -cvf MostPopularPath.jar -C MostPopularPath/ .
	hadoop jar MostPopularPath.jar MostPopularPath /user/oborcher/access_log MostPopularPath/output
	hdfs dfs -cat MostPopularPath/output/part-r-00000 | less

BiggestUser: BiggestUser.java BiggestUserMapper.java BiggestUserReducer.java
	mkdir BiggestUser
	javac -classpath $(HADOOP_CLASSPATH) -d BiggestUser/ BiggestUser.java BiggestUserReducer.java BiggestUserMapper.java
	jar -cvf BiggestUser.jar -C BiggestUser/ .
	hadoop jar BiggestUser.jar BiggestUser /user/oborcher/access_log BiggestUser/output
	hdfs dfs -cat BiggestUser/output/part-r-00000 | less


clean:
	rm -rf MostPopularPath
	rm MostPopularPath.jar
	hdfs dfs -rm -R MostPopularPath
	rm -rf BiggestUser
	rm BiggestUser.jar
	hdfs dfs -rm -R BiggestUser



