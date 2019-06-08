package rs.novisad.crimetime.global;

import rs.novisad.crimetime.entity.Cluster;

import java.util.ArrayList;
import java.util.List;

public class var {
    public static List<Cluster> clusters = new ArrayList<>();

    static {
        clusters.add(
                new Cluster("centar",
                        ("zmaj jovin, dunvask, ilije ognjanovic, laze telecko, milticev, pasicev, zlatne gred," +
                                "njegosev, futosk, jevrejsk, safarnikov, masarikov, jovana subotic," +
                                "vojvode bojovic, lukijana musick, vojvode supljikc, vuka karadzic," +
                                "pap pavl, narodnih heroj, zeleznick, vase stajic").split(","))
        );
        clusters.add(
                new Cluster("stari grad",
                        ("maksima gorkog, radnick, strazilovsk, stevana music, vojvode misic, jovana djordjevic," +
                                "vladike platon, sutjesk, zarka zrenjanin").split(","))
        );
        clusters.add(
                new Cluster("kej",
                        "kej zrtava racij, beogradski, suncani".split(","))
        );
        clusters.add(
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
        clusters.add(
                new Cluster("salaj",
                        ("kisack, jovana cvijic, dositejev, karadjordjev," +
                                "branka radicevic, radoja domanovic, partizanska").split(","))
        );
        clusters.add(
                new Cluster("grbavic",
                        ("brace ribnik, mise dimitrijevic, danila kis, vojvodjansk, lasla gal, " +
                                "puskinov, gogoljev, tolstojev, vojvode knicanin").split(","))
        );
        clusters.add(
                new Cluster("liman I i II",
                        ("fruskogorsk, narodnog front, dr sime milosevic, drage spasic," +
                                "dragise brasovan, dr ivana ribar, resavsk, ravanick").split(","))
        );
        clusters.add(
                new Cluster("liman III i IV",
                        "balzakov, podgorick, sekspirov, iva andric, 1300 kaplar, banovic strahinj".split(","))
        );
        clusters.add(
                new Cluster("sajmist",
                        ("cara dusan, hajduk veljkov, novosadskog sajm, rumenack, brace popovic," +
                                "hadzi ruminov, stevana mokranjc").split(","))
        );
        clusters.add(
                new Cluster("telep",
                        ("ilariona ruvarc, heroja pinkij, cirila i metodij, petefi sandor," +
                                "subotick, vrsack, adi endre, bogdana suput, fejes klar, sarplaninsk").split(","))
        );
        clusters.add(
                new Cluster("detelinar",
                        ("kornelija stankovic, milenka grcic, janka veselinovic, janka cmelik, " +
                                "dr svetislava kasapinovic, ilije bircanin").split(","))
        );
        clusters.add(
                new Cluster("novo naselj",
                        ("radivoja rase raduljkov, stojana novakovic, djordja niksica johanm todora javanovic," +
                                "mileve maric, kace dejanovic, seljackih bun, brace dronjak, bate brkic, " +
                                "dusana danilovic, stevana hladnog, antuna urban").split(","))
        );
        clusters.add(
                new Cluster("adic",
                        "branka copic, slavujev, sime solaj, podunavsk".split(","))
        );
        clusters.add(
                new Cluster("klis",
                        ("temerinsk, sentandrejsk, primorsk, otokara kersovani," +
                                "cenejsk, paje radosavljevic, velebitsk, savsk").split(","))
        );
    }
}
