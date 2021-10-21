package com.company;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String input = "start;end;summary;description;\n" +
                "\"2017-10-07 00:00:00\";\"2017-10-07 23:59:59\";\"Mezinárodní den zvířat \";\"<p>Oslavte s námi Mezinárodní den zvířat oblíbeným karnevalem. Pohybová, hudební a výtvarná dílna. Od 9.30 hodin ve Vzdělávacím centru. Vstup za 1 Kč pro děti do 15 let se zvířecí maskou.</p>\"\n" +
                "\"2017-10-07 00:00:00\";\"2017-10-07 23:59:59\";\"Rok Tamařina slůněte Rudiho\";\"<p>Oslava rok života nejmladšího slona, které se v pražské zoo narodilo a bylo zde také počato.</p>\"\n" +
                "\"2017-10-07 00:00:00\";\"2017-10-07 23:59:59\";\"Rok Tamařina slůněte Rudiho\";\"''\"\n" +
                "\"2017-10-07 11:30:00\";\"2017-10-07 12:30:00\";\"Rok Tamařina slůněte Rudiho\";\"<p>Oslava prvního roku nejmladšího slona, které se v pražské zoo narodilo a bylo zde také počato.&nbsp;</p>\"\n" +
                "\"2017-10-15 10:00:00\";\"2017-10-15 15:00:00\";\"Inteligence u zvířat\";\"<p>Vyrobte obyvatelům Rezervace Bororo hlavolam! Ve 12.00 a 14.30 si nenechte ujít jejich speciální vystoupení.</p>\"\n" +
                "\"2017-10-21 16:00:00\";\"2017-10-21 19:00:00\";\"Diwali, svátek světel \";\"<p>Večerním lampionovým průvodem připomeneme hinduistický svátek.&nbsp;Začátek akce v 16.00. Vstup za 1,- Kč pro návštěvníky s lampionem.</p>\"\n" +
                "\"2017-10-26 00:00:00\";\"2017-10-26 23:59:59\";\"Podzimní prázdniny v zoo\";\"<p>Program pro děti v rámci podzimních prázdnin; speciální vystoupení na Bororu, komentovaná krmení, podzimní dílny.&nbsp;Těšit se také můžete na dýňové hody u zvířat - speciální komentované krmení.</p>\"\n" +
                "\"2017-10-28 00:00:00\";\"2017-10-28 23:59:59\";\"Lvi ve státním znaku\";\"<p>Oslava státnosti u zvířat, která máme ve státním znaku. Informační stánek – historie chovu lvů v lidské péči, ohrožený poddruh lev indický a jeho chov v Zoo Praha.&nbsp;Ve 13.00 hodin budou spuštěny piñaty do venkovního výběhu lvů indických, spojené se speciálním komentováním.</p>\"\n" +
                "\"2017-10-29 10:30:00\";\"2017-10-29 11:30:00\";\"Přijďte s námi oslavit Slávkovy 33. narozeniny\";\"<p>Slavnostní setkání v Pavilonu hrochů. Začátek v 10.30 hod.</p>\"\n" +
                "\"2017-11-04 00:00:00\";\"2017-11-04 23:59:59\";\"Vyrobte si krmítko \";\"<p>Dílny v zoo, vyrobte si ptačí krmítko.</p>\"\n" +
                "\"2017-11-06 00:00:00\";\"2017-11-06 23:59:59\";\"Dny dětských sponzorů\";\"<p>V zoo se setkají dětské kolektivy, které přispěly na zdejší chov zvířat.</p>\"\n" +
                "\"2017-11-11 10:00:00\";\"2017-11-11 15:00:00\";\"Do zoo se zavřenýma očima\";\"<p>Ukázka přírodnin a živých zvířat formou hmatové výstavy. Od 10.00 do 15.00 hodin ve Vzdělávacím centru.</p>\"\n" +
                "\"2017-11-17 12:00:00\";\"2017-11-17 15:00:00\";\"Svátek studentů v Zoo Praha\";\"<p>Setkání se zvířaty a přidaná cvičení zvířat v amfiteátru Rezervace Bororo ve 12.00 a 14.30 hodin.</p>\"\n" +
                "\"2017-11-19 00:00:00\";\"2017-11-19 23:59:59\";\"Den chovatelů želv\";\"<p>Cyklus přednášek a setkání s želvími chovanci, chovatelská poradna.</p>\"\n" +
                "\"2017-11-26 00:00:00\";\"2017-11-26 23:59:59\";\"Adventní dílny\";\"<p>Užijte si klidnou předvánoční atmosféru v zoo a vyrobte si vlastní adventní věnec.</p>\"\n" +
                "\"2017-11-27 00:00:00\";\"2017-11-27 23:59:59\";\"Školy zdobí zoo\";\"<p>Vybrané přihlášené školy zdobí zoo vlastními výrobky.</p>\"\n" +
                "\"2017-12-03 00:00:00\";\"2017-12-03 23:59:59\";\"Adventní dílny\";\"<p>Užijte si klidnou předvánoční atmosféru v zoo a vyrobte si vlastní adventní věnec. Z kapacitních důvodů je nutné se předem objednat.</p>\"\n" +
                "\"2017-12-03 16:00:00\";\"2017-12-03 18:00:00\";\"Vánoční strom se rozsvěcí\";\"<p>V 16 hodin zahájíme advent slavnostním rozsvícením stromu.</p>\"\n" +
                "\"2017-12-05 00:00:00\";\"2017-12-05 23:59:59\";\"Po zoo chodí Mikuláš\";\"<p>Byly vaše ratolesti hodné? Všechny prohřešky malých i velkých návštěvníků sečtou spravedliví čerti, hodné odmění Mikuláš s andělem.</p>\"\n" +
                "\"2017-12-10 00:00:00\";\"2017-12-10 23:59:59\";\"Den sponzorů Zoo Praha a Den pro Troju\";\"<p>Tradiční setkání přátel, sponzorů a partnerů pražské zoo a obyvatel m. č. Praha Troja, předání cen Richard a vyhlášení výsledků fotosoutěže.</p>\"\n" +
                "\"2017-12-16 00:00:00\";\"2017-12-16 23:59:59\";\"Užijte si předvánoční klid v zoo\";\"<p>Odneste si ze zoo Betlémské světlo a vyrobte si vánoční přání.</p>\"\n" +
                "\"2017-12-17 00:00:00\";\"2017-12-17 23:59:59\";\"Nuru slaví 5. narozeniny\";\"<p>Oslava půlkulatých narozenin gorilího samečka Nurua.</p>\"\n" +
                "\"2017-12-24 00:00:00\";\"2017-12-24 23:59:59\";\"Štědrý den v zoo\";\"<p>Přineste zvířatům dárky a podívejte se, co dostanou k Vánocům.</p>\"\n" +
                "\"2017-12-28 00:00:00\";\"2017-12-28 23:59:59\";\"Vánoce u zvířat\";\"''\"\n";

        ArrayList<Action> arrayList = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        long sum = 0;

        String[] firstSplit = input.split("\n");
        for (int i = 1; i < firstSplit.length; i++) {
            String line = firstSplit[i].replace("\"", "");
            String[] lineAsArray = line.split(";");

            Action action = new Action(LocalDateTime.parse(lineAsArray[0], format), LocalDateTime.parse(lineAsArray[1], format), lineAsArray[2], lineAsArray[3]);
            arrayList.add(action);
        }

        for (int i = 0; i < arrayList.size(); i++) {
            Action action = arrayList.get(i);

            Duration duration = Duration.between(action.getStart(), action.getEnd());

            long a = duration.toHours();
            long b = duration.toMinutes();
            long c = duration.toSeconds();
            long[] all = {a + b + c};
            for (long j : all){
                sum += j;
            }
        }
        System.out.println("Average of seconds:" + (sum) / arrayList.size());
    }
}
