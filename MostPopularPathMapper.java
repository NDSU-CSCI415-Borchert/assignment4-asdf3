import java.io.IOException;
import java.math.BigDecimal;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MostPopularPathMapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(" ");
//	String[] fields2 = fields[1].split("\\s+");
//        String ipaddress  = fields[0];
//	String clientid = fields[1];
//	String username = fields[2];
//	String requesttime = fields[3];
	String address = fields[6];
//	String statuscode = fields[7];
//	String size = fields[8];
	context.write(new Text(address), new Text(address));
    }
}
