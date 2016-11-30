package de.ubleipzig.solr.transform;

import java.io.IOException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.transform.DocTransformer;
import org.apache.solr.response.transform.TransformerFactory;

/**
 * In solrconfig.xml add:
 *
 * <transformer name="example" class="de.ubleipzig.solr.transform.HelloTransformerFactory">
 *     <str name="location">Russia</str>
 * </transformer>
 * 
 * @author Martin Czygan
 */
public class HelloTransformerFactory extends TransformerFactory {

    private String location = "ExampleTransformer";
    
    @Override
    public DocTransformer create(String string, SolrParams sp, SolrQueryRequest sqr) {
        return new ExampleTransformer();
    }
    
    class ExampleTransformer extends DocTransformer {

        @Override
        public String getName() {
            return "Hello Transformer!";
        }

        @Override
        public void transform(SolrDocument sd, int i, float f) throws IOException {
            sd.addField("ExampleTransformer", "From " + location + " with Love!");
        }
    }
}
