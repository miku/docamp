package de.ubleipzig.solr.transform;

import java.io.IOException;
import java.util.logging.Logger;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.transform.DocTransformer;
import org.apache.solr.response.transform.TransformerFactory;
import org.apache.solr.search.SolrIndexSearcher;

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

    // location
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
        return new ExampleTransformer(sqr);
    }

    class ExampleTransformer extends DocTransformer {

        Logger log = Logger.getLogger(ExampleTransformer.class.getName());

        private SolrQueryRequest sqr;

        ExampleTransformer(SolrQueryRequest sqr) {
            this.sqr = sqr;
        }

        @Override
        public void transform(SolrDocument sd, int i) throws IOException {
            SolrIndexSearcher searcher = this.sqr.getSearcher();
            String[] fields = new String[]{"name"};

            // TODO(miku): create a suitable query
            // Query query = new MoreLikeThisQuery("alice", fields, searcher.getSchema().getAnalyzer(), "name");
            Query query = new MatchAllDocsQuery();
            TopDocs docs = searcher.search(query, 3);

            log.info("Hello from ExampleTransformer: " + query.toString());

            // Query on the fly.
            sd.addField("ExampleTransformer", "From " + location + " with Love!");
            sd.addField("ExampleTransformerHeader", String.format("%d", docs.totalHits));
        }

        @Override
        public String getName() {
            return "Hello Transformer!";
        }
    }
}
