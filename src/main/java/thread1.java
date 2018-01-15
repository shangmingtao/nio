import java.text.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class thread1 implements Runnable{
	
	public Date data = null;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//		System.out.println(demo.getlocal());

		while(true){
			try {
				if (!sf.format(data).equals(demo.getlocal().format(data))){
					System.out.println(Thread.currentThread().getName() + "error" + sf.format(data) + "      " + demo.getlocal().format(data));
//				break;
				}else{
//				System.out.println("success");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}


	static MySimpleDataFormat mySimpleDataFormat = new MySimpleDataFormat();




	public static void main(String[] args) throws InterruptedException {
		String str1 = new String("123");
		String str2 = new String("123");
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		System.out.println(str1.equals(str2));
		System.out.println(str1 == str2);


		System.out.println("-----------------Object-----------------");
		System.out.println(new Object());
		System.out.println(new Object());

		System.out.println("-----------------Format-----------------");
		System.out.println(new Format() {
			@Override
			public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
				return null;
			}

			@Override
			public Object parseObject(String source, ParsePosition pos) {
				return null;
			}
		});
		System.out.println(new Format() {
			@Override
			public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
				return null;
			}

			@Override
			public Object parseObject(String source, ParsePosition pos) {
				return null;
			}
		});
		System.out.println("-----------------DateFormat-----------------");
		NumberFormat numberFormat = new NumberFormat() {
			@Override
			public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
				return null;
			}

			@Override
			public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
				return null;
			}

			@Override
			public Number parse(String source, ParsePosition parsePosition) {
				return null;
			}
		};
		NumberFormat numberFormat2 = new NumberFormat() {
			@Override
			public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
				return null;
			}

			@Override
			public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
				return null;
			}

			@Override
			public Number parse(String source, ParsePosition parsePosition) {
				return null;
			}
		};

		Thread.currentThread().sleep(1000);
		DateFormat dateFormat1 = new DateFormat() {
			@Override
			public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
				return null;
			}

			@Override
			public Date parse(String source, ParsePosition pos) {
				return null;
			}
		};
		dateFormat1.setNumberFormat(numberFormat);

		DateFormat dateFormat2 = new DateFormat() {
			@Override
			public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
				return null;
			}

			@Override
			public Date parse(String source, ParsePosition pos) {
				return null;
			}
		};
		dateFormat2.setNumberFormat(numberFormat2);

		numberFormat.setMaximumIntegerDigits(50);
		numberFormat2.setMaximumIntegerDigits(45);
		System.out.println(dateFormat1);
		System.out.println(dateFormat2);

		System.out.println("-----------------SimpleDateFormat-----------------");
		System.out.println(new SimpleDateFormat());
		System.out.println(new SimpleDateFormat("yy-mm-dd"));
		System.out.println(new SimpleDateFormat() == new SimpleDateFormat());


		System.out.println("-----------------SimpleDateFormat-----------------");
		System.out.println(new MySimpleDataFormat());
		System.out.println(new MySimpleDataFormat());
		System.out.println(new MySimpleDataFormat() == new MySimpleDataFormat());

		System.out.println("-----------------SimpleDateFormat-----------------");

		System.out.println(mySimpleDataFormat);
		System.out.println(mySimpleDataFormat);

	}

}
