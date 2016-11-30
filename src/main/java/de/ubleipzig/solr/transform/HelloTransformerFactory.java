package de.ubleipzig.solr.transform;

import java.io.IOException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.common.util.StrUtils;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.transform.DocTransformer;
import org.apache.solr.response.transform.TransformerFactory;

/**
 * In solrconfig.xml add:
 *
 * <transformer name="example" class="de.ubleipzig.solr.transform.HelloTransformerFactory">
 * <str name="location">Russia</str>
 * </transformer>
 *
 * @author Martin Czygan
 */
public class HelloTransformerFactory extends TransformerFactory {

    private String location = "ExampleTransformer";

    @Override
    public void init(NamedList args) {
        super.init(args);
        SolrParams params = SolrParams.toSolrParams(args);
        String str = params.get("location");
        if (str != null) {
            location = str;
        }
    }

    @Override
    public DocTransformer create(String string, SolrParams sp, SolrQueryRequest sqr) {
        return new ExampleTransformer();
    }

    class ExampleTransformer extends DocTransformer {

        @Override
        public void transform(SolrDocument sd, int i) throws IOException {
            sd.addField("ExampleTransformer", "From " + location + " with Love!");
        }

        @Override
        public String getName() {
            return "Hello Transformer!";
        }
    }
}
