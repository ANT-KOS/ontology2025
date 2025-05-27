package org.example;

import org.eclipse.rdf4j.model.*;
import org.eclipse.rdf4j.model.impl.*;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.util.Values;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.rio.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static String trimStringQuotes(String s ) {
        if (s.startsWith("\"")) {
            s = s.substring(1);
        }
        if(s.endsWith("\"")) {
            s = s.substring(0, s.length()-1);
        }
        return s;
    }

    public static void main(String[] args) throws Exception {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ValueFactory vf = SimpleValueFactory.getInstance();

        String ns = "http://example.org/";
        ModelBuilder builder = new ModelBuilder();
        builder.setNamespace("ex", ns);

        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\v.michailidis\\IdeaProjects\\MiniProject\\src\\main\\java\\org\\example\\survey.min.csv"));
        String[] properties = {
                "ResponseId",
                "MainBranch",
                "Age",
                "Employment",
                "RemoteWork",
                "EdLevel",
                "LearnCodeOnline",
                "TechDoc",
                "YearsCode",
                "YearsCodePro",
                "DevType",
                "OrgSize",
                "Country",
                "Currency",
                "CompTotal",
                "LanguageHaveWorkedWith",
                "LanguageWantToWorkWith",
                "LanguageAdmired",
                "DatabaseHaveWorkedWith",
                "DatabaseWantToWorkWith",
                "DatabaseAdmired",
                "PlatformHaveWorkedWith",
                "PlatformWantToWorkWith",
                "PlatformAdmired",
                "WebframeHaveWorkedWith",
                "WebframeWantToWorkWith",
                "WebframeAdmired",
                "EmbeddedHaveWorkedWith",
                "EmbeddedWantToWorkWith",
                "EmbeddedAdmired",
                "ToolsTechHaveWorkedWith",
                "ToolsTechWantToWorkWith",
                "ToolsTechAdmired",
                "NEWCollabToolsHaveWorkedWith",
                "NEWCollabToolsWantToWorkWith",
                "NEWCollabToolsAdmired",
                "OpSysPersonal use",
                "OpSysProfessional use",
                "OfficeStackAsyncHaveWorkedWith",
                "OfficeStackAsyncWantToWorkWith",
                "OfficeStackAsyncAdmired",
                "OfficeStackSyncHaveWorkedWith",
                "OfficeStackSyncWantToWorkWith",
                "OfficeStackSyncAdmired",
                "AiSearchDevHaveWorkedWith",
                "AiSearchDevWantToWorkWith",
                "AiSearchDevAdmired",
                "WorkExp",
                "Industry",
                "JobSat",
        };
        Map<String, Integer> propertyIndexMap = new HashMap<>();
        String[] headers = lines.get(0).split(",", -1); // header row
        for (String property: properties) {
            propertyIndexMap.put(property, java.util.Arrays.asList(headers).indexOf(property));
        }

        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",", -1);
            builder.subject(ns + "dev/" + parts[0])
                .add(RDF.TYPE, "ex:Developer")
                .add("ex:hasMainBranch", trimStringQuotes(parts[propertyIndexMap.get("MainBranch")]))
                .add("ex:age", trimStringQuotes(parts[propertyIndexMap.get("Age")]))
                .add("ex:compensation", trimStringQuotes(parts[propertyIndexMap.get("CompTotal")]))
                .add("ex:devType", trimStringQuotes(parts[propertyIndexMap.get("DevType")]))
                .add("ex:hasCountry", trimStringQuotes(parts[propertyIndexMap.get("Country")]))
                .add("ex:hasEducationLevel", trimStringQuotes(parts[propertyIndexMap.get("EdLevel")]))
                .add("ex:hasEmploymentType", trimStringQuotes(parts[propertyIndexMap.get("Employment")]))
                .add("ex:hasJobSatisfaction", trimStringQuotes(parts[propertyIndexMap.get("JobSat")]))
                .add("ex:industry", trimStringQuotes(parts[propertyIndexMap.get("Industry")]))
                .add("ex:orgSize", trimStringQuotes(parts[propertyIndexMap.get("OrgSize")]))
                .add("ex:remoteWork", trimStringQuotes(parts[propertyIndexMap.get("RemoteWork")]))
                .add("ex:usesCurrency", trimStringQuotes(parts[propertyIndexMap.get("Currency")]))
                .add("ex:workExperience", trimStringQuotes(parts[propertyIndexMap.get("WorkExp")]))
                .add("ex:yearsAsPro", trimStringQuotes(parts[propertyIndexMap.get("YearsCodePro")]))
                .add("ex:yearsOfCoding", trimStringQuotes(parts[propertyIndexMap.get("YearsCode")]));

        }

        try (OutputStream outputStream = new FileOutputStream("output.ttl")) {
            Rio.write(builder.build(), outputStream, RDFFormat.TURTLE);
            System.out.println("RDF written to output.ttl using OutputStream.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}