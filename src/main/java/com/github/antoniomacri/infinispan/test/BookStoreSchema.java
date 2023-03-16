package com.github.antoniomacri.infinispan.test;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(includeClasses = {Book.class, Author.class}, schemaPackageName = "book_sample")
interface BookStoreSchema extends GeneratedSchema {
}
