package tech.saltandsugar.springboottest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringbootTestApplicationTests {
//	@BeforeAll
//	static void setUp(){
//	}
//	@DisplayName("CSVファイルが読み込めるか")
//	@ParameterizedTest
//	@ValueSource(strings = {"src/test/java/tech/saltandsugar/springboottest/resources/kyushoku_202002.csv"
//			               ,"src/test/java/tech/saltandsugar/springboottest/resources/kyushoku_202012.csv"})
//	void CSVファイルの読み込みと検索(String filelocation) {
//		DataProcessor dataProcessor = new DataProcessor();
//		ArrayList result = dataProcessor.CSV_toArrayList(Paths.get(filelocation));
//		Assertions.assertNotNull(result);
//
//		Assertions.assertNotEquals(-1,dataProcessor.doSearchLine(result,3,"1"));
//	}
//	@Test
//	@DisplayName("toArrayが動くか")
//	void toArrayが動くか(){
//		//Dateの作成
//		Date date = null;
//		String DateStr = "2020/12/01";
//		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd");
//		try {
//			date = sdformat.parse(DateStr);
//		} catch (ParseException e) {
//			Assertions.fail("TestError",e);
//		}
//		//ここからテスト
//		Utility utility = new Utility();
//		Assertions.assertArrayEquals(new String[]{"2020", "12", "1"},utility.toArray(date));
//	}
}
