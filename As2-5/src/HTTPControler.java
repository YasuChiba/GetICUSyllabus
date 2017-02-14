import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by YasuhiraChiba on 2016/11/06.
 */
public class HTTPControler {
    BasicCookieStore cookieStore = new BasicCookieStore();
    HtmlDataEntity DataEntity;
    _Util util = new _Util();
    String saveDirectory = "";
    Main component;
    String year = Constatnts.year;

    public HTTPControler(Main component) {
        this.component = component;
    }

    public void PrintLog(String s) {
        component.PrintLog(s);
    }


    public int Login(String id, String pass) {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        try {
            HttpGet httpget = new HttpGet("https://oam.icu.ac.jp/sso/UI/Login");
            CloseableHttpResponse response1 = httpclient.execute(httpget);
            try {
                HttpEntity entity = response1.getEntity();

                PrintLog("\n" + "Login form get: " + response1.getStatusLine());
                EntityUtils.consume(entity);

                PrintLog("\n" + "Initial set of cookies:");
                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()) {
                    PrintLog("\n" + "None");
                } else {
                    for (int i = 0; i < cookies.size(); i++) {
                        PrintLog("\n" + "- " + cookies.get(i).toString());
                    }
                }
            } finally {
                response1.close();
            }

            HttpUriRequest login = RequestBuilder.post()
                    .setUri(new URI("https://oam.icu.ac.jp/sso/UI/Login"))
                    .addParameter("IDToken1", id)
                    .addParameter("IDToken2", pass)
                    .build();
            CloseableHttpResponse response2 = httpclient.execute(login);
            try {
                HttpEntity entity = response2.getEntity();

                PrintLog("\n" + "Login form get: " + response2.getStatusLine());
                EntityUtils.consume(entity);

                PrintLog("\n" + "Post logon cookies:");
                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty() || cookies.size() == 2) {
                    PrintLog("\n" + "Login error");
                    return -1;
                } else {
                    for (int i = 0; i < cookies.size(); i++) {
                        PrintLog("\n" + "- " + cookies.get(i).toString());
                    }
                }
            } finally {
                response2.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return -1;
        }
        try {
            httpclient.close();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }


    }


    public int initForGetSyllabus(String saveDirectory) {
        this.saveDirectory = saveDirectory;
        getSyllabus(-1);
        getSyllabus(1);

        int lastPage = checkLastPage();
        return lastPage;
    }


    public HtmlDataEntity getSyllabus(int page) {
        String pageNum = "";
        String eventTarget = "";
        if (page != -1) {
            pageNum = "Page$" + page;
        } else if (page == -1 || page == 1) {
            pageNum = "";
        }
        if (DataEntity == null) {
            DataEntity = new HtmlDataEntity();
            DataEntity.setEventValidation("");
            DataEntity.setPreviousPage("");
            DataEntity.setViewState("");
        }


        try {
            HttpUriRequest login;
            if (page >= 2) {

                login = RequestBuilder.post()
                        .setUri(new URI("https://campus.icu.ac.jp/icumap/ehb/SearchCO.aspx"))
                        .addParameter("__EVENTTARGET", "ctl00$ContentPlaceHolder1$grv_course")
                        .addParameter("__EVENTARGUMENT", pageNum)
                        .addParameter("__LASTFOCUS", "")
                        .addParameter("__VIEWSTATE", DataEntity.getViewState())
                        .addParameter("__PREVIOUSPAGE", DataEntity.getPreviousPage())
                        .addParameter("__EVENTVALIDATION", DataEntity.getEventValidation())
                        .addParameter("ctl00$hid_member_kbn", "0")
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_year", year)
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_term", "")
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_major", "")
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_fdnam", "")
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_period", "")
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_week", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_schedule", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_instructor", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_course_title", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_course_no", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_reg_no", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_lang", "")
                        .addParameter("ctl00$ContentPlaceHolder1$ddlPageSize", "10")
                        .build();
            } else {
                login = RequestBuilder.post()
                        .setUri(new URI("https://campus.icu.ac.jp/icumap/ehb/SearchCO.aspx"))
                        .addParameter("__EVENTTARGET", eventTarget)
                        .addParameter("__EVENTARGUMENT", pageNum)
                        .addParameter("__LASTFOCUS", "")
                        .addParameter("__VIEWSTATE", DataEntity.getViewState())
                        .addParameter("__PREVIOUSPAGE", DataEntity.getPreviousPage())
                        .addParameter("__EVENTVALIDATION", DataEntity.getEventValidation())
                        .addParameter("ctl00$hid_member_kbn", "0")
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_year", year)
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_term", "")
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_major", "")
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_fdnam", "")
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_period", "")
                        .addParameter("ctl00$ContentPlaceHolder1$ddl_week", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_schedule", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_instructor", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_course_title", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_course_no", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_reg_no", "")
                        .addParameter("ctl00$ContentPlaceHolder1$txt_lang", "")
                        .addParameter("ctl00$ContentPlaceHolder1$btn_search", "Search")
                        .addParameter("ctl00$ContentPlaceHolder1$ddlPageSize", "10")
                        .build();

            }


            CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();


            CloseableHttpResponse response2 = httpclient.execute(login);
            HttpEntity entity = response2.getEntity();

            String s = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            DataEntity = anyaliseStringForViewStateETC(s);

            if (page != -1) {
                util.saveToFile(s, "filePageNUM=" + page + ".html", saveDirectory);
                PrintLog(saveDirectory + "/" + "filePageNUM=" + page + ".html");

            }
            EntityUtils.consume(entity);

            response2.close();


        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return DataEntity;
    }


    private int checkLastPage() {
        int lastPageNum = -1;

        try {
            HttpUriRequest login = RequestBuilder.post()
                    .setUri(new URI("https://campus.icu.ac.jp/icumap/ehb/SearchCO.aspx"))
                    .addParameter("__EVENTTARGET", "ctl00$ContentPlaceHolder1$grv_course")
                    .addParameter("__EVENTARGUMENT", "Page$Last")
                    .addParameter("__LASTFOCUS", "")
                    .addParameter("__VIEWSTATE", DataEntity.getViewState())      //¥¥¥¥¥¥¥¥¥
                    .addParameter("__PREVIOUSPAGE", DataEntity.getPreviousPage())//¥¥¥¥¥¥¥¥¥
                    .addParameter("__EVENTVALIDATION", DataEntity.getEventValidation())//¥¥¥¥¥¥¥¥¥
                    .addParameter("ctl00$hid_member_kbn", "0")
                    .addParameter("ctl00$ContentPlaceHolder1$ddl_year", year)
                    .addParameter("ctl00$ContentPlaceHolder1$ddl_term", "")
                    .addParameter("ctl00$ContentPlaceHolder1$ddl_major", "")
                    .addParameter("ctl00$ContentPlaceHolder1$ddl_fdnam", "")
                    .addParameter("ctl00$ContentPlaceHolder1$ddl_period", "")
                    .addParameter("ctl00$ContentPlaceHolder1$ddl_week", "")
                    .addParameter("ctl00$ContentPlaceHolder1$txt_schedule", "")
                    .addParameter("ctl00$ContentPlaceHolder1$txt_instructor", "")
                    .addParameter("ctl00$ContentPlaceHolder1$txt_course_title", "")
                    .addParameter("ctl00$ContentPlaceHolder1$txt_course_no", "")
                    .addParameter("ctl00$ContentPlaceHolder1$txt_reg_no", "")
                    .addParameter("ctl00$ContentPlaceHolder1$txt_lang", "")
                    .addParameter("ctl00$ContentPlaceHolder1$ddlPageSize", "10")
                    .build();

            CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();

            CloseableHttpResponse response2 = httpclient.execute(login);
            HttpEntity entity = response2.getEntity();

            String s = EntityUtils.toString(entity, StandardCharsets.UTF_8);

            int lastPos = s.indexOf("</td><td><span>");
            String strLastPos = s.substring(lastPos + 15);
            lastPos = strLastPos.indexOf("</span>");
            strLastPos = strLastPos.substring(0, lastPos);
            PrintLog("\n" + "last pos =" + strLastPos);
            lastPageNum = Integer.valueOf(strLastPos);


            EntityUtils.consume(entity);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return lastPageNum;
    }


    private HtmlDataEntity anyaliseStringForViewStateETC(String str) {
        String strForviewState = str;
        String strForpreviousPage = str;
        String strForEventval = str;
        int viewStatePos = -1;
        int previousPagePos = -1;
        int eventValidationPos = -1;

        HtmlDataEntity data = new HtmlDataEntity();

        viewStatePos = str.indexOf("id=\"__VIEWSTATE\" value=\"");
        strForviewState = str.substring(viewStatePos + 24);
        viewStatePos = strForviewState.indexOf(" />");
        strForviewState = strForviewState.substring(0, viewStatePos - 1);
        data.setViewState(strForviewState);


        previousPagePos = str.indexOf("id=\"__PREVIOUSPAGE\" value=\"");
        strForpreviousPage = str.substring(previousPagePos + 27);
        previousPagePos = strForpreviousPage.indexOf(" />");
        strForpreviousPage = strForpreviousPage.substring(0, previousPagePos - 1);
        data.setPreviousPage(strForpreviousPage);

        eventValidationPos = str.indexOf("id=\"__EVENTVALIDATION\" value=\"");
        strForEventval = str.substring(eventValidationPos + 30);
        eventValidationPos = strForEventval.indexOf(" />");
        strForEventval = strForEventval.substring(0, eventValidationPos - 1);
        data.setEventValidation(strForEventval);


        return data;
    }


}
