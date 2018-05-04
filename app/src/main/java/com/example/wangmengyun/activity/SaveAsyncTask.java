package com.example.wangmengyun.activity;
/**
 *
 * 这个activity实现了把注册用户存储到MongoDB中
 */

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;

import com.example.wangmengyun.Bean.UserBean;


public class SaveAsyncTask extends AsyncTask<UserBean, Void, Boolean> {

    @Override
    protected Boolean doInBackground(UserBean... arg0) {
        try
        {
            UserBean user = arg0[0];

            QueryBuilder qb = new QueryBuilder();

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(qb.buildContactsSaveURL());

            StringEntity params =new StringEntity(qb.createContact(user));
            request.addHeader("content-type", "application/json");

            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            if(response.getStatusLine().getStatusCode()<205)
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (Exception e) {
            //e.getCause();
            String val = e.getMessage();
            String val2 = val;
            return false;
        }
    }

    public class QueryBuilder {

        public String getDatabaseName() {
            return "mongo_connect";
        }

        public String getApiKey() {
            return "l2vM_qRqK1SfqIbQsV9zq4PJVINybEvN";
        }

        public String getBaseUrl()
        {
            return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
        }

        /**
         * Completes the formating of your URL and adds your API key at the end
         * @return
         */
        public String docApiKeyUrl()
        {
            return "?apiKey="+getApiKey();
        }

        /**
         * Returns the users collection
         * @return
         */
        public String documentRequest()
        {
            return "users";
        }

        /**
         * Builds a complete URL using the methods specified above
         * @return
         */
        public String buildContactsSaveURL()
        {
            return getBaseUrl()+documentRequest()+docApiKeyUrl();
        }

        /**
         * Formats the contact details for MongoHQ Posting
         * @param user: Details of the person
         * @return
         */
        public String createContact(UserBean user)
        {
            return String
                    .format("{\"document\"  : {\"userName\": \"%s\", "
                                    + "\"sex\": \"%s\", \"psw\": \"%s\"}, \"safe\" : true}",
                            user.userName, user.sex, user.psw);
        }



    }

}
