README
======

Playing with SOLR document transformations.

To test manually, use a minimal SOLR setup, e.g. https://github.com/hsch/solr-minimal.

Adjust solrconfig.xml, in the logs you should see something like:

```
1162 [coreLoadExecutor-3-thread-1] INFO  org.apache.solr.core.SolrResourceLoader  - ...
   Adding 'file:/xxxx/xxxx/code/hsch/solr-minimal/contrib/lib/docamp-1.0-SNAPSHOT.jar' to classloader
```

Basic query:

```
{
  "responseHeader": {
    "status": 0,
    "QTime": 24
  },
  "response": {
    "numFound": 2,
    "start": 0,
    "docs": [
      {
        "id": "1",
        "name": "alice",
        "ExampleTransformer": "From Russia with Love!"
      },
      {
        "id": "2",
        "name": "bob",
        "ExampleTransformer": "From Russia with Love!"
      }
    ]
  }
}
```
