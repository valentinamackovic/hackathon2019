package rs.novisad.crimetime;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rs.novisad.crimetime.crawler.Extractor;
import rs.novisad.crimetime.crawler.JSONArticleParser;
import rs.novisad.crimetime.entity.Aricle;
import rs.novisad.crimetime.entity.Cluster;
import rs.novisad.crimetime.entity.*;
import rs.novisad.crimetime.global.var;
import rs.novisad.crimetime.helper.AddressProcessor;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class CrimetimeApplication {
	
	public static ArrayList<Aricle> articles=new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(CrimetimeApplication.class, args);
		
		var.clusters.add(
				new Cluster("centar",
						("zmaj jovin, dunvask, ilije ognjanovic, laze telecko, milticev, pasicev, zlatne gred," +
								"njegosev, futosk, jevrejsk, safarnikov, masarikov, jovana subotic," +
								"vojvode bojovic, lukijana musick, vojvode supljikc, vuka karadzic," +
								"pap pavl, narodnih heroj, zeleznick, vase stajic").split(","))
		);
		var.clusters.add(
				new Cluster("stari grad",
						("maksima gorkog, radnick, strazilovsk, stevana music, vojvode misic, jovana djordjevic," +
								"vladike platon, sutjesk, zarka zrenjanin").split(","))
		);
		var.clusters.add(
				new Cluster("kej",
						"kej zrtava racij, beogradski, suncani".split(","))
		);
		var.clusters.add(
				new Cluster("podbar",
						("temerinsk, gundulicev, almask, kosovsk, tekelijin, marka miljanov," +
								"save vukovic, bele njiv, djordja rajkovic, filipa visnjic," +
								"svetosavsk, marice srpsk, sterijin, baranjsk, peck, petra kocic," +
								"milosa obilic, pavla stamatovic, koce kolarov, jase ignjatovic, " +
								"partijarha carnojevic, hadzi cvetic, jug bogdan, skerlicev," +
								"danicicev, sumadijsk, episkopa visarion, zarka vasiljevic, " +
								"stevana milovanov, milosa bajic, dusana vasiljev," +
								"milana rakic, venizelosov, koste hadzi mladj, zemljane cuprij," +
								"marka nesic").split(","))
		);
		var.clusters.add(
				new Cluster("salaj",
						("kisack, jovana cvijic, dositejev, karadjordjev," +
								"branka radicevic, radoja domanovic, partizanska").split(","))
		);
		var.clusters.add(
				new Cluster("grbavic",
						("brace ribnik, mise dimitrijevic, danila kis, vojvodjansk, lasla gal, " +
								"puskinov, gogoljev, tolstojev, vojvode knicanin").split(","))
		);
		var.clusters.add(
				new Cluster("liman I i II",
						("fruskogorsk, narodnog front, dr sime milosevic, drage spasic," +
								"dragise brasovan, dr ivana ribar, resavsk, ravanick").split(","))
		);
		var.clusters.add(
				new Cluster("liman III i IV",
						"balzakov, podgorick, sekspirov, iva andric, 1300 kaplar, banovic strahinj".split(","))
		);
		var.clusters.add(
				new Cluster("sajmist",
						("cara dusan, hajduk veljkov, novosadskog sajm, rumenack, brace popovic," +
								"hadzi ruminov, stevana mokranjc").split(","))
		);
		var.clusters.add(
				new Cluster("telep",
						("ilariona ruvarc, heroja pinkij, cirila i metodij, petefi sandor," +
								"subotick, vrsack, adi endre, bogdana suput, fejes klar, sarplaninsk").split(","))
		);
		var.clusters.add(
				new Cluster("detelinar",
						("kornelija stankovic, milenka grcic, janka veselinovic, janka cmelik, " +
								"dr svetislava kasapinovic, ilije bircanin").split(","))
		);
		var.clusters.add(
				new Cluster("novo naselj",
						("radivoja rase raduljkov, stojana novakovic, djordja niksica johanm todora javanovic," +
								"mileve maric, kace dejanovic, seljackih bun, brace dronjak, bate brkic, " +
								"dusana danilovic, stevana hladnog, antuna urban").split(","))
		);
		var.clusters.add(
				new Cluster("adic",
						"branka copic, slavujev, sime solaj, podunavsk".split(","))
		);
		var.clusters.add(
				new Cluster("klis",
						("temerinsk, sentandrejsk, primorsk, otokara kersovani," +
								"cenejsk, paje radosavljevic, velebitsk, savsk").split(","))
		);var.clusters.add(
				new Cluster("petrovarad",
						("temerinsk, sentandrejsk, primorsk, otokara kersovani," +
								"cenejsk, paje radosavljevic, velebitsk, savsk").split(","))
		);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println("START DATE: " + df.format(new Date()));

		//System.out.println("https://novisad.com/vesti");

		Extractor noviSadComEx = new Extractor("news", "h1", "single", true);
		noviSadComEx.getPageLinks("https://novisad.com/vesti");
		noviSadComEx.getArticles();
		noviSadComEx.writeToFile("novisad.com1.json");

		for (int i = 2; i <= 5; i++) {
			//System.out.println("https://novisad.com/vesti?page=" + i);

			Extractor noviSadCom2Ex = new Extractor("news", "h1", "single", true);
			noviSadCom2Ex.getPageLinks("https://novisad.com/vesti?page=" + i);
			noviSadCom2Ex.getArticles();
			noviSadCom2Ex.writeToFile("novisad.com" + i + ".json");
		}

		//System.out.println("https://www.021.rs/");

//
//		for (int i = 20; i < 120; i+= 20) {
//			Extractor Nula21RsEx = new Extractor("article_title", "h1", "story", false);
//			Nula21RsEx.getPageLinks("https://www.021.rs/Novi%20Sad/4/" + i);
//			Nula21RsEx.getArticles();
//			Nula21RsEx.writeToFile("021.rs" +  i + ".json");
//		}

		JSONArticleParser parser=new JSONArticleParser();

		System.out.println("END DATE: " + df.format(new Date()));
		System.out.println("NUMBER OF ARTICLES: " + Extractor.numberOfAricles);
		System.out.println("END OF RESEARCH");
		
		System.out.println("............");
		
		
		
		for (Aricle string : noviSadComEx.getAllArticles()) {
			//System.out.println(string.getContent());
			System.out.println(AddressProcessor.parseAdress(string.getContent()));
		}
	}

}
