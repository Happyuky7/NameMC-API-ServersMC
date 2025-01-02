package com.github.happyuky7.nameMCAPIServersMC.utils;

/*
 * Code by: Happyuky7
 * GitHub: https://github.com/Happyuky7
 * License: Custom
 * Link: https://github.com/Happyuky7/DownloadTranslationsJavaClassFromGitHub
 */

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class DownloadTranslations {

    // URL Base for the GitHub API to get the list of available translations
    // https://api.github.com/repos/<username>/<repository>/contents/langs
    private static final String GITHUB_LANGS_API_URL = "https://api.github.com/repos/Happyuky7/NameMC-API-ServersMC/contents/langs";
    private static final String LOCAL_LANGS_PATH = "plugins/NameMCAPI/langs/";

    // Download the translations from GitHub
    public static void downloadTranslations() {

        List<String> availableLanguages = fetchAvailableLanguagesFromGitHub();

        for (String lang : availableLanguages) {
            String filePath = LOCAL_LANGS_PATH + lang + ".yml";
            //downloadTranslationFile("https://raw.githubusercontent.com/<username>/<repository>/<branch>/langs/" + lang + "<fileExtension>", filePath);
            downloadTranslationFile("https://raw.githubusercontent.com/Happyuky7/NameMC-API-ServersMC/master/langs/" + lang + ".yml", filePath);
        }
    }

    // Method to fetch the list of available languages from GitHub
    private static List<String> fetchAvailableLanguagesFromGitHub() {

        List<String> languages = new ArrayList<>();

        try {

            // Request the list of files from the GitHub API
            URL url = new URL(GITHUB_LANGS_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Read the response from the API and parse it as JSON
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response and extract the names of the .yml files
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject file = jsonArray.getJSONObject(i);

                // Check if the file is a .yml file
                if (file.getString("name").endsWith(".yml")) {
                    // Add the language name to the list, removing the .yml extension
                    languages.add(file.getString("name").replace(".yml", ""));
                }
            }
        } catch (IOException e) {
            System.err.println("Error fetching available languages from GitHub");
            e.printStackTrace();
        }
        return languages;
    }

    private static void downloadTranslationFile(String fileUrl, String savePath) {
        File localFile = new File(savePath);
        File parentDir = localFile.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (localFile.exists()) {
            System.out.println("File already exists: " + savePath);
            return;
        }

        try {

            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new FileWriter(savePath))) {

                String line;
                while ((line = in.readLine()) != null) {
                    out.write(line);
                    out.newLine();
                }

                System.out.println("Downloaded file: " + savePath);
            }
        } catch (IOException e) {
            System.err.println("Error downloading translation file: " + fileUrl);
            e.printStackTrace();
        }
    }
}
