package rs.novisad.crimetime.global;

import rs.novisad.crimetime.entity.Cluster;

import java.util.ArrayList;
import java.util.List;

public class var {
    public static List<Cluster> clusters = new ArrayList<>();

    static {
//        clusters.add(
//                new Cluster("Centar",
//                        ("zmaj jovin, dunvask, ilije ognjanovic, laze telecko, milticev, pasicev, zlatne gred," +
//                                "njegosev, futosk, jevrejsk, safarnikov, masarikov, jovana subotic," +
//                                "vojvode bojovic, lukijana musick, vojvode supljikc, vuka karadzic," +
//                                "pap pavl, narodnih heroj, zeleznick, vase stajic").split(","),"centar",45.250790, 19.848328)
//        );
        clusters.add(
                new Cluster("Стари град",
                        ("maksima gorkog, radnick, strazilovsk, stevana music, vojvode misic, jovana djordjevic," +
                                "vladike platon, sutjesk, zarka zrenjanin").split(","), "stari grad",45.250790, 19.848328)
        );
        clusters.add(
                new Cluster("Кеј",
                        "kej zrtava racij, beogradski, suncani".split(","), "kej")
        );
        clusters.add(
                new Cluster("Подбара",
                        ("temerinsk, gundulicev, almask, kosovsk, tekelijin, marka miljanov," +
                                "save vukovic, bele njiv, djordja rajkovic, filipa visnjic," +
                                "svetosavsk, marice srpsk, sterijin, baranjsk, peck, petra kocic," +
                                "milosa obilic, pavla stamatovic, koce kolarov, jase ignjatovic, " +
                                "partijarha carnojevic, hadzi cvetic, jug bogdan, skerlicev," +
                                "danicicev, sumadijsk, episkopa visarion, zarka vasiljevic, " +
                                "stevana milovanov, milosa bajic, dusana vasiljev," +
                                "milana rakic, venizelosov, koste hadzi mladj, zemljane cuprij," +
                                "marka nesic").split(","), "podbar",45.263031, 19.849161)
        );
        clusters.add(
                new Cluster("Салајка",
                        ("kisack, jovana cvijic, dositejev, karadjordjev," +
                                "branka radicevic, radoja domanovic, partizanska").split(","), "salaj",45.269927, 19.836195)
        );
        clusters.add(
                new Cluster("Грбавица",
                        ("brace ribnik, mise dimitrijevic, danila kis, vojvodjansk, lasla gal, " +
                                "puskinov, gogoljev, tolstojev, vojvode knicanin").split(","), "grbavic",45.246157, 19.833241)
        );
        clusters.add(
                new Cluster("Лиман 1 и 2",
                        ("fruskogorsk, narodnog front, dr sime milosevic, drage spasic," +
                                "dragise brasovan, dr ivana ribar, resavsk, ravanick").split(","), "liman I i II",19.826750,19.836880)
        );
        clusters.add(
                new Cluster("Лиман 3 и 4",
                        "balzakov, podgorick, sekspirov, iva andric, 1300 kaplar, banovic strahinj".split(","), "Liman III i IV",45.237750, 19.826750)
        );
        clusters.add(
                new Cluster("Сајмиште",
                        ("cara dusan, hajduk veljkov, novosadskog sajm, rumenack, brace popovic," +
                                "hadzi ruminov, stevana mokranjc").split(","), "sajmist",45.254153, 19.822002)
        );
        clusters.add(
                new Cluster("Телеп",
                        ("ilariona ruvarc, heroja pinkij, cirila i metodij, petefi sandor," +
                                "subotick, vrsack, adi endre, bogdana suput, fejes klar, sarplaninsk").split(","), "telep", 45.236660, 19.810889)
        );
        clusters.add(
                new Cluster("Детелинара",
                        ("kornelija stankovic, milenka grcic, janka veselinovic, janka cmelik, " +
                                "dr svetislava kasapinovic, ilije bircanin").split(","), "detelinar", 45.259143, 19.815119)
        );
        clusters.add(
                new Cluster("Ново насеље",
                        ("radivoja rase raduljkov, stojana novakovic, djordja niksica johanm todora javanovic," +
                                "mileve maric, kace dejanovic, seljackih bun, brace dronjak, bate brkic, " +
                                "dusana danilovic, stevana hladnog, antuna urban").split(","), "novom naselj", 45.252782, 19.800046)
        );
        clusters.add(
                new Cluster("Адице",
                        "branka copic, slavujev, sime solaj, podunavsk".split(","), "adic", 45.235423, 19.778067)
        );
        clusters.add(
                new Cluster("Клиса",
                        ("temerinsk, sentandrejsk, primorsk, otokara kersovani," +
                                "cenejsk, paje radosavljevic, velebitsk, savsk").split(","), "klis", 45.302924, 19.824081)
        );
    }
}
