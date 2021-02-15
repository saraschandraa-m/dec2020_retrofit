package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class News {

    public String status;
    public int totalResults;
    public ArrayList<Article> articles;

    public static News parseNewsValue(String response) {
        News item = new News();

        try {
            JSONObject jsonObject = new JSONObject(response);

            item.status = jsonObject.optString("status");
            item.totalResults = jsonObject.optInt("totalResults");

            item.articles = new ArrayList<>();

            JSONArray articlesArray = jsonObject.optJSONArray("articles");
            if (articlesArray.length() > 0) {
                for (int i = 0; i < articlesArray.length(); i++) {
                    JSONObject articleObject = articlesArray.optJSONObject(i);

                    Article article = Article.parseArticle(articleObject);
                    item.articles.add(article);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return item;
    }
}
