import java.io.IOException;
import java.math.BigDecimal;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class BiggestUserMapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(" ");
//	if(fields.length > 9){
            String ipaddress  = fields[0];
//	    String clientid = fields[1];
//	    String username = fields[2];
//	    String requesttime = fields[3];
//	    String location = fields[4];
//	    String requestline = fields[5] + " " + fields[6] + " " + fields[7];
//	    String statuscode = fields[7];
	    String size = fields[fields.length - 1];
	    context.write(new Text(ipaddress), new Text(size));
//	}
    }
}
