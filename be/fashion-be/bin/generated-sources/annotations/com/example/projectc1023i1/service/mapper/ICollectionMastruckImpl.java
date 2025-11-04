package com.example.projectc1023i1.service.mapper;

import com.example.projectc1023i1.Dto.get_data.collection_maptruck.CollectionMaptruck;
import com.example.projectc1023i1.Dto.get_data.collection_maptruck.ProductCollectionMapStruck;
import com.example.projectc1023i1.model.Collection;
import com.example.projectc1023i1.model.ProductCollection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-04T22:02:55+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251023-0518, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ICollectionMastruckImpl implements ICollectionMastruck {

    @Override
    public CollectionMaptruck converToCollectionMaptruck(Collection collection) {
        if ( collection == null ) {
            return null;
        }

        CollectionMaptruck.CollectionMaptruckBuilder collectionMaptruck = CollectionMaptruck.builder();

        collectionMaptruck.collectionId( collection.getCollectionId() );
        collectionMaptruck.productCollections( mapProductCollections( collection.getProductCollections() ) );
        collectionMaptruck.description( collection.getDescription() );
        collectionMaptruck.endDate( collection.getEndDate() );
        collectionMaptruck.imageUrl( collection.getImageUrl() );
        collectionMaptruck.isActive( collection.getIsActive() );
        collectionMaptruck.name( collection.getName() );
        collectionMaptruck.startDate( collection.getStartDate() );

        return collectionMaptruck.build();
    }

    @Override
    public List<CollectionMaptruck> toCollectionMaptruckList(List<Collection> collections) {
        if ( collections == null ) {
            return null;
        }

        List<CollectionMaptruck> list = new ArrayList<CollectionMaptruck>( collections.size() );
        for ( Collection collection : collections ) {
            list.add( converToCollectionMaptruck( collection ) );
        }

        return list;
    }

    @Override
    public ProductCollectionMapStruck mapToProductCollectionMapStruck(ProductCollection productCollection) {
        if ( productCollection == null ) {
            return null;
        }

        ProductCollectionMapStruck.ProductCollectionMapStruckBuilder productCollectionMapStruck = ProductCollectionMapStruck.builder();

        productCollectionMapStruck.id( productCollection.getId() );
        productCollectionMapStruck.product( productCollection.getProduct() );

        return productCollectionMapStruck.build();
    }
}
