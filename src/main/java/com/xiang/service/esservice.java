package com.xiang.service;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class esservice {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    public ArrayList<Map<String,Object>> get(String keyword, int page , int size) throws IOException {
        SearchRequest searchRequest=new SearchRequest("jd");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(page);
        builder.size(size);

        //精准匹配
        TermQueryBuilder query = QueryBuilders.termQuery("title", keyword);
        builder.query(query);
        builder.timeout(new TimeValue(60,TimeUnit.SECONDS));


        //设置高亮
        HighlightBuilder builder1 = new HighlightBuilder();
        builder1.field("title");
        builder1.requireFieldMatch(false);//多个高亮显示
        builder1.preTags("<span style='color" +
                ":red'>");
        builder1.postTags("</span>");
        builder.highlighter(builder1);


        searchRequest.source(builder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);



        ArrayList<Map<String,Object>> list=new ArrayList<>();
        for (SearchHit searchHit:search.getHits()
        ) {
//            System.out.println(searchHit+"111111");
//            System.out.println(searchHit.toString()+"222");
            Map<String, HighlightField> fields = searchHit.getHighlightFields();//获得高亮数据集合
            System.out.println(fields);
            HighlightField title = fields.get("title"); //高亮字段
//            System.out.println(title+"=====");
            Map<String, Object> source = searchHit.getSourceAsMap();//没有高亮的数据
//            System.out.println(source+"3333333");
            if (title!=null)
            {

                Text[] fragments = title.fragments(); //高亮字段的高亮片段
                String n_title="";
                for (Text text: fragments
                ) {

//                    System.out.println(text);

                    n_title=n_title+text;

                }
                source.put("title",n_title);


            }

            list.add(source);

        }

        return list;
    }



}


