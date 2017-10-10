package com.lavrisha.tracker;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * SqlProject is a Querydsl query type for SqlProject
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class SqlProject extends com.querydsl.sql.RelationalPathBase<SqlProject> {

    private static final long serialVersionUID = -1464209106;

    public static final SqlProject project = new SqlProject("PROJECT");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<SqlProject> constraint1 = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<SqlStory> _fkmsqatheg9j5hb2vi429cmvgis = createInvForeignKey(id, "PROJECT_ID");

    public SqlProject(String variable) {
        super(SqlProject.class, forVariable(variable), "PUBLIC", "PROJECT");
        addMetadata();
    }

    public SqlProject(String variable, String schema, String table) {
        super(SqlProject.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public SqlProject(String variable, String schema) {
        super(SqlProject.class, forVariable(variable), schema, "PROJECT");
        addMetadata();
    }

    public SqlProject(Path<? extends SqlProject> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "PROJECT");
        addMetadata();
    }

    public SqlProject(PathMetadata metadata) {
        super(SqlProject.class, metadata, "PUBLIC", "PROJECT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(2).ofType(Types.VARCHAR).withSize(255));
    }

}

