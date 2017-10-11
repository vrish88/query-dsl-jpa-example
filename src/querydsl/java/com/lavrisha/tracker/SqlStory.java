package com.lavrisha.tracker;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * SqlStory is a Querydsl query type for SqlStory
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class SqlStory extends com.querydsl.sql.RelationalPathBase<SqlStory> {

    private static final long serialVersionUID = 1856053322;

    public static final SqlStory story = new SqlStory("STORY");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath owner = createString("owner");

    public final NumberPath<Integer> points = createNumber("points", Integer.class);

    public final NumberPath<Integer> projectId = createNumber("projectId", Integer.class);

    public final DatePath<java.sql.Date> rejectedDate = createDate("rejectedDate", java.sql.Date.class);

    public final StringPath requester = createString("requester");

    public final StringPath state = createString("state");

    public final StringPath title = createString("title");

    public final com.querydsl.sql.PrimaryKey<SqlStory> constraint4 = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<SqlProject> fkmsqatheg9j5hb2vi429cmvgis = createForeignKey(projectId, "ID");

    public SqlStory(String variable) {
        super(SqlStory.class, forVariable(variable), "PUBLIC", "STORY");
        addMetadata();
    }

    public SqlStory(String variable, String schema, String table) {
        super(SqlStory.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public SqlStory(String variable, String schema) {
        super(SqlStory.class, forVariable(variable), schema, "STORY");
        addMetadata();
    }

    public SqlStory(Path<? extends SqlStory> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "STORY");
        addMetadata();
    }

    public SqlStory(PathMetadata metadata) {
        super(SqlStory.class, metadata, "PUBLIC", "STORY");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(description, ColumnMetadata.named("DESCRIPTION").withIndex(2).ofType(Types.VARCHAR).withSize(255));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(owner, ColumnMetadata.named("OWNER").withIndex(3).ofType(Types.VARCHAR).withSize(255));
        addMetadata(points, ColumnMetadata.named("POINTS").withIndex(4).ofType(Types.INTEGER).withSize(10));
        addMetadata(projectId, ColumnMetadata.named("PROJECT_ID").withIndex(9).ofType(Types.INTEGER).withSize(10));
        addMetadata(rejectedDate, ColumnMetadata.named("REJECTED_DATE").withIndex(5).ofType(Types.DATE).withSize(8));
        addMetadata(requester, ColumnMetadata.named("REQUESTER").withIndex(6).ofType(Types.VARCHAR).withSize(255));
        addMetadata(state, ColumnMetadata.named("STATE").withIndex(7).ofType(Types.VARCHAR).withSize(255));
        addMetadata(title, ColumnMetadata.named("TITLE").withIndex(8).ofType(Types.VARCHAR).withSize(255));
    }

}

