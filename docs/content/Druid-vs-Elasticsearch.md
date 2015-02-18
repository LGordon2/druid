---
layout: doc_page
---

Druid vs Elasticsearch
======================

We are not experts on Elasticsearch, if anything is incorrect about our portrayal, please let us know on the mailing list or via some other means.

Elasticsearch is a search server based on Apache Lucene. It provides full text search for schema-free documents and provides access to raw event level data. Elasticsearch provides support for analytics, but the resource requirements for data ingestion and aggregation will be higher than those of Druid.

Druid focuses on aggregations for OLAP work flows. Druid's ingestion is lock-free, and supports a wide range of analytic operations. Druid has some basic search support for structured event data.
