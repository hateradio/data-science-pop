package edu.csula.datascience.acquisition;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.csula.population.Collector;
import edu.csula.population.Source;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by williamsalinas on 4/23/16.
 */
public class WorldBankPopulationCollectorTest {

    private Collector<MockBankCountryData, MockBankCountryData> collector;
    private Source<MockBankCountryData> source;

    @Before
    public void setup() {
        collector = new MockWorldBankPopulationCollector();
        source = new MockWorldBankPopulationSource();
    }


    @Test
    public  void mungee() throws Exception{

        List<MockBankCountryData> list = (List<MockBankCountryData>) collector.mungee(source.next());

        System.out.println(list.get(0).getRecords().size());

        //Assert.assertEquals(list.get(0).getRecords().size(), 19);

    }

//    @Test
//    public void save_source_to_mongodb() throws Exception {
//
//        Collection<MockBankCountryData> src= source.next();
//        collector.save(src);
//
//        MongoClient mongoClient;
//        MongoDatabase database;
//        MongoCollection<Document> collection;
//
//        mongoClient = new MongoClient();
//        database = mongoClient.getDatabase("test-countries-db");
//        collection = database.getCollection("test_world_bank_population");
//
//
//        Assert.assertEquals(collection.count(),1);
//
//        database.getCollection("test_world_bank_population").drop();
//
//    }

    @Test
    public void check_get_source() throws Exception{
        MockWorldBankPopulationSource w = new MockWorldBankPopulationSource();
       //w.requestWorldBankCountryPopulation("AR","2010").getRecords().size();


        ArrayList<MockWorldBankPopulationRecord> list = new ArrayList<MockWorldBankPopulationRecord>();
        list.add(0,new MockWorldBankPopulationRecord("41222875","2010"));
        list.add(1,new MockWorldBankPopulationRecord("40798641","2009"));
        MockBankCountryData a = new MockBankCountryData("AR",list);

        for (int i = 0; i < w.requestWorldBankCountryPopulation("AR","2010").getRecords().size(); i++) {
            Assert.assertEquals(w.requestWorldBankCountryPopulation("AR","2010").getRecords().get(i).getDate(), list.get(i).getDate());
            Assert.assertEquals(w.requestWorldBankCountryPopulation("AR","2010").getRecords().get(i).getValue(), list.get(i).getValue());
        }

    }

}