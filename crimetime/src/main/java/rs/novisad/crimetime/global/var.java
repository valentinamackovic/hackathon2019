package rs.novisad.crimetime.global;

import rs.novisad.crimetime.entity.Cluster;

import java.util.ArrayList;
import java.util.List;

public class var {
    public static List<Cluster> clusters = new ArrayList<>();

    static {
        clusters.add(
                new Cluster("Centar",
                        ("zmaj jovin, dunvask, ilije ognjanovic, laze telecko, milticev, pasicev, zlatne gred," +
                                "njegosev, futosk, jevrejsk, safarnikov, masarikov, jovana subotic," +
                                "vojvode bojovic, lukijana musick, vojvode supljikc, vuka karadzic," +
                                "pap pavl, narodnih heroj, zeleznick, vase stajic").split(","),"centar",19.844440,45.255860)
        );
        clusters.add(
                new Cluster("Stari grad",
                        ("maksima gorkog, radnick, strazilovsk, stevana music, vojvode misic, jovana djordjevic," +
                                "vladike platon, sutjesk, zarka zrenjanin").split(","), "stari grad",19.847560,45.250900)
        );
        clusters.add(
                new Cluster("Kej",
                        "kej zrtava racij, beogradski, suncani".split(","), "kej")
        );
        clusters.add(
                new Cluster("Podbara",
                        ("temerinsk, gundulicev, almask, kosovsk, tekelijin, marka miljanov," +
                                "save vukovic, bele njiv, djordja rajkovic, filipa visnjic," +
                                "svetosavsk, marice srpsk, sterijin, baranjsk, peck, petra kocic," +
                                "milosa obilic, pavla stamatovic, koce kolarov, jase ignjatovic, " +
                                "partijarha carnojevic, hadzi cvetic, jug bogdan, skerlicev," +
                                "danicicev, sumadijsk, episkopa visarion, zarka vasiljevic, " +
                                "stevana milovanov, milosa bajic, dusana vasiljev," +
                                "milana rakic, venizelosov, koste hadzi mladj, zemljane cuprij," +
                                "marka nesic").split(","), "podbar",19.846950,45.262080)
        );
        clusters.add(
                new Cluster("Salajka",
                        ("kisack, jovana cvijic, dositejev, karadjordjev," +
                                "branka radicevic, radoja domanovic, partizanska").split(","), "salaj",19.841290,45.261380)
        );
        clusters.add(
                new Cluster("Grbavica",
                        ("brace ribnik, mise dimitrijevic, danila kis, vojvodjansk, lasla gal, " +
                                "puskinov, gogoljev, tolstojev, vojvode knicanin").split(","), "grbavic",19.833750,45.246710)
        );
        clusters.add(
                new Cluster("liman I i II",
                        ("fruskogorsk, narodnog front, dr sime milosevic, drage spasic," +
                                "dragise brasovan, dr ivana ribar, resavsk, ravanick").split(","), "liman I i II",19.836880,45.240240)
        );
        clusters.add(
                new Cluster("liman III i IV",
                        "balzakov, podgorick, sekspirov, iva andric, 1300 kaplar, banovic strahinj".split(","), "Liman III i IV",19.826750,45.237750)
        );
        clusters.add(
                new Cluster("Sajmiste",
                        ("cara dusan, hajduk veljkov, novosadskog sajm, rumenack, brace popovic," +
                                "hadzi ruminov, stevana mokranjc").split(","), "sajmist",19.833310,45.255210)
        );
        clusters.add(
                new Cluster("Telep",
                        ("ilariona ruvarc, heroja pinkij, cirila i metodij, petefi sandor," +
                                "subotick, vrsack, adi endre, bogdana suput, fejes klar, sarplaninsk").split(","), "telep")
        );
        clusters.add(
                new Cluster("Detelinara",
                        ("kornelija stankovic, milenka grcic, janka veselinovic, janka cmelik, " +
                                "dr svetislava kasapinovic, ilije bircanin").split(","), "detelinar")
        );
        clusters.add(
                new Cluster("Novo naselje",
                        ("radivoja rase raduljkov, stojana novakovic, djordja niksica johanm todora javanovic," +
                                "mileve maric, kace dejanovic, seljackih bun, brace dronjak, bate brkic, " +
                                "dusana danilovic, stevana hladnog, antuna urban").split(","), "novo naselj")
        );
        clusters.add(
                new Cluster("Adice",
                        "branka copic, slavujev, sime solaj, podunavsk".split(","), "adic")
        );
        clusters.add(
                new Cluster("Klisa",
                        ("temerinsk, sentandrejsk, primorsk, otokara kersovani," +
                                "cenejsk, paje radosavljevic, velebitsk, savsk").split(","), "klis")
        );
    }
}
