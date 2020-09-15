package cn.itcast.test;

import cn.itcast.dao.IntentionDao;
import cn.itcast.util.face.ClientToken;
import cn.itcast.util.face.FaceUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSolr {
    @Autowired
    private HttpSolrClient client;
    @Test
    public void testToken() throws IOException, SolrServerException {
        System.out.println("solr test..");
        String education="工业";
        String intention="产品经理";
        String resume="";
        SolrQuery query = new SolrQuery();
        query.set("q","*:*");
        if(education!=null && ! education.equals("")){
            query.set("fq","education:"+education);
            System.out.println("education部分执行了!");
        }
        if(intention!=null && ! intention.equals("")){
            query.set("fq","intention:"+intention);
            System.out.println("intention部分执行了!");
        }
        if(resume!=null && !resume.equals("")){
            query.set("fq","resume:"+resume);
            System.out.println("resume部分执行了!");
        }
//        query.set("fq","education:"+education);
//        query.set("fq","intention:"+intention);
        QueryResponse res = client.query(query);
        SolrDocumentList rlt = res.getResults();
        for (SolrDocument doc : rlt) {
//            System.out.println(doc.get("bookName")+"---"+doc.get("author"));
            System.out.println(doc);
        }
//        String intention="";
//        String education="";
//        String resume="";
//        SolrQuery query = new SolrQuery();
//        query.set("q","education:工业");
//        if(intention!=null || !intention.equals("")){
//            query.set("fq","education:"+education);
//        }
//        if(intention!=null || !intention.equals("")){
//            query.set("fq","intention:"+intention);
//        }
//        if(resume!=null || !resume.equals("")){
//            query.set("fq","resume:"+resume);
//        }
//
//        QueryResponse res = client.query(query);
//        SolrDocumentList rlt = res.getResults();
//        for (SolrDocument doc : rlt) {
////            System.out.println(doc.get("bookName")+"---"+doc.get("author"));
//            System.out.println(doc);
//        }

    }

}
