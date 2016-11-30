README
======

Playing with SOLR document transformations.

To test manually, use a minimal SOLR setup, e.g. https://github.com/hsch/solr-minimal.

Adjust solrconfig.xml, in the logs you should see something like:

```
1162 [coreLoadExecutor-3-thread-1] INFO  org.apache.solr.core.SolrResourceLoader  - Adding 'file:/home/tir/code/hsch/solr-minimal/contrib/lib/docamp-1.0-SNAPSHOT.jar' to classloader
```