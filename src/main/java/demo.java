import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class demo {
	
	public static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();

	public static SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static SimpleDateFormat getlocal(){
//		if(local.get() == null){
//			local.set(s);
//		}
//		System.out.println(new SimpleDateFormat());
//		System.out.println(new SimpleDateFormat());
//		System.out.println(new SimpleDateFormat());
//		System.out.println(new demo());
//		System.out.println(new demo());
//		System.out.println(new demo());
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		return s;
	}
	
	public static void main(String[] args) {

		thread1 t1= new thread1();
		thread1 t2= new thread1();
		thread1 t3= new thread1();

		Date data1 = new Date(1515068966571L);
		Date data2 = new Date(1525068966571L);
		Date data3 = new Date(1535068966571L);

		t1.data = data1;
		t2.data = data2;
		t3.data = data3;

		System.out.println(data1);
		System.out.println(data2);
		System.out.println(data3);

		new Thread(t1).start();
		new Thread(t2).start();
		new Thread(t3).start();
	}



}
