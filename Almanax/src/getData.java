import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class getData {

	public static void main(String[] args) throws IOException, ParseException {

		String web = "https://www.krosmoz.com/es/almanax/";
		String date = "2022-01-01";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date, formatter);
		String game = "?game=dofus";
		String path = "c:\\almanax.txt";
		for (int i = 0; i < 365; i++) {
			date = localDate.toString();
			InputStream inStream = new URL(web + date + game).openStream();
			Document doc = Jsoup.parse(inStream, "UTF-8", web + date + game);
			doc.getElementsByClass("more-infos").remove();
			Elements e = doc.getElementsByClass("more");
			System.out.println(localDate + " - " + e.get(0).text());
			Files.writeString(Paths.get(path), localDate + " - " + e.get(0).text() + "\n", StandardOpenOption.APPEND);
			localDate = localDate.plusDays(1);
		}
	}
}
