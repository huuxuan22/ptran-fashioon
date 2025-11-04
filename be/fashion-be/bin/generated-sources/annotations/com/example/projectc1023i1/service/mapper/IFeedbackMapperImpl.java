package com.example.projectc1023i1.service.mapper;

import com.example.projectc1023i1.Dto.get_data.FeedbackDTO;
import com.example.projectc1023i1.Dto.get_data.FeedbackMediaDTO;
import com.example.projectc1023i1.Dto.get_data.FeedbackMessageDTO;
import com.example.projectc1023i1.Dto.get_data.ProductSmpleDTO;
import com.example.projectc1023i1.Dto.get_data.UserSimpleDTO;
import com.example.projectc1023i1.model.Feedback;
import com.example.projectc1023i1.model.FeedbackMedia;
import com.example.projectc1023i1.model.FeedbackMessage;
import com.example.projectc1023i1.model.Product;
import com.example.projectc1023i1.model.Users;
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
public class IFeedbackMapperImpl implements IFeedbackMapper {

    @Override
    public FeedbackDTO toFeedbackDTO(Feedback feedback) {
        if ( feedback == null ) {
            return null;
        }

        FeedbackDTO feedbackDTO = new FeedbackDTO();

        feedbackDTO.setFeedbackMessages( feedbackMessageListToFeedbackMessageDTOList( feedback.getFeedbackMessages() ) );
        feedbackDTO.setUser( toUserSimpleDTO( feedback.getUser() ) );
        feedbackDTO.setProduct( toProductSimpleDTO( feedback.getProduct() ) );
        feedbackDTO.setFeedbackMedia( feedbackMediaListToFeedbackMediaDTOList( feedback.getFeedbackMedia() ) );
        feedbackDTO.setContent( feedback.getContent() );
        feedbackDTO.setCreateAt( feedback.getCreateAt() );
        feedbackDTO.setFeedbackId( feedback.getFeedbackId() );
        feedbackDTO.setRating( feedback.getRating() );
        feedbackDTO.setStatus( feedback.getStatus() );
        feedbackDTO.setTitle( feedback.getTitle() );
        feedbackDTO.setUpdateAt( feedback.getUpdateAt() );

        return feedbackDTO;
    }

    @Override
    public FeedbackMessageDTO toFeedbackMessageDTO(FeedbackMessage message) {
        if ( message == null ) {
            return null;
        }

        FeedbackMessageDTO feedbackMessageDTO = new FeedbackMessageDTO();

        feedbackMessageDTO.setCreatedAt( message.getCreatedAt() );
        feedbackMessageDTO.setFbMessageId( message.getFbMessageId() );
        feedbackMessageDTO.setMessage( message.getMessage() );
        feedbackMessageDTO.setSender( message.getSender() );

        return feedbackMessageDTO;
    }

    @Override
    public UserSimpleDTO toUserSimpleDTO(Users user) {
        if ( user == null ) {
            return null;
        }

        UserSimpleDTO userSimpleDTO = new UserSimpleDTO();

        userSimpleDTO.setFullName( user.getFullName() );
        userSimpleDTO.setImgUrl( user.getImgUrl() );
        userSimpleDTO.setNumberphone( user.getNumberphone() );
        userSimpleDTO.setUserId( user.getUserId() );

        return userSimpleDTO;
    }

    @Override
    public ProductSmpleDTO toProductSimpleDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductSmpleDTO productSmpleDTO = new ProductSmpleDTO();

        productSmpleDTO.setProductId( product.getProductId() );
        productSmpleDTO.setProductName( product.getProductName() );

        return productSmpleDTO;
    }

    @Override
    public FeedbackMediaDTO toFeedbackMediaDTO(FeedbackMedia feedbackMedia) {
        if ( feedbackMedia == null ) {
            return null;
        }

        FeedbackMediaDTO feedbackMediaDTO = new FeedbackMediaDTO();

        feedbackMediaDTO.setCreatedAt( feedbackMedia.getCreatedAt() );
        feedbackMediaDTO.setFbMessage( feedbackMedia.getFbMessage() );
        feedbackMediaDTO.setFeedback( feedbackMedia.getFeedback() );
        feedbackMediaDTO.setMediaId( feedbackMedia.getMediaId() );
        feedbackMediaDTO.setMediaType( feedbackMedia.getMediaType() );
        feedbackMediaDTO.setMediaUrl( feedbackMedia.getMediaUrl() );

        return feedbackMediaDTO;
    }

    protected List<FeedbackMessageDTO> feedbackMessageListToFeedbackMessageDTOList(List<FeedbackMessage> list) {
        if ( list == null ) {
            return null;
        }

        List<FeedbackMessageDTO> list1 = new ArrayList<FeedbackMessageDTO>( list.size() );
        for ( FeedbackMessage feedbackMessage : list ) {
            list1.add( toFeedbackMessageDTO( feedbackMessage ) );
        }

        return list1;
    }

    protected List<FeedbackMediaDTO> feedbackMediaListToFeedbackMediaDTOList(List<FeedbackMedia> list) {
        if ( list == null ) {
            return null;
        }

        List<FeedbackMediaDTO> list1 = new ArrayList<FeedbackMediaDTO>( list.size() );
        for ( FeedbackMedia feedbackMedia : list ) {
            list1.add( toFeedbackMediaDTO( feedbackMedia ) );
        }

        return list1;
    }
}
