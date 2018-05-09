package ru.demi.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;
import org.hibernate.cfg.ImprovedNamingStrategy;

public class ImplicitNamingStrategyImpl extends ImplicitNamingStrategyJpaCompliantImpl {

    public static final ImplicitNamingStrategy INSTANCE = new ImplicitNamingStrategyImpl();

    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
        return toIdentifier( transformAttributePath( source.getAttributePath() ), source.getBuildingContext() );
    }

    @Override
    protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
        stringForm = ImprovedNamingStrategy.INSTANCE.columnName(stringForm);
        return buildingContext.getMetadataCollector()
            .getDatabase()
            .getJdbcEnvironment()
            .getIdentifierHelper()
            .toIdentifier( stringForm );
    }
}
