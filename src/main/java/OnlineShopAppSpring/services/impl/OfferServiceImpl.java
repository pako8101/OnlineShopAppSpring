package OnlineShopAppSpring.services.impl;

import OnlineShopAppSpring.models.dtos.BoughtOffersDto;
import OnlineShopAppSpring.models.dtos.MyOfferDto;
import OnlineShopAppSpring.models.dtos.OfferHomeDto;
import OnlineShopAppSpring.models.dtos.OtherOffersDto;
import OnlineShopAppSpring.models.entities.Offer;
import OnlineShopAppSpring.models.entities.User;
import OnlineShopAppSpring.models.enums.ConditionName;
import OnlineShopAppSpring.models.service.OfferServiceModel;
import OnlineShopAppSpring.models.view.OfferViewModel;
import OnlineShopAppSpring.repositories.OfferRepository;
import OnlineShopAppSpring.repositories.UserRepository;
import OnlineShopAppSpring.services.ConditionService;
import OnlineShopAppSpring.services.LoggedUser;
import OnlineShopAppSpring.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
private final ModelMapper modelMapper;
private final ConditionService conditionService;
private final OfferRepository offerRepository;
private final LoggedUser loggedUser;
private final UserRepository userRepository;

    public OfferServiceImpl(ModelMapper modelMapper,
                            ConditionService conditionService, OfferRepository offerRepository, LoggedUser loggedUser, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.conditionService = conditionService;

        this.offerRepository = offerRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    @Override
    public void addOffer(OfferServiceModel offerServiceModel) {
        Offer offer = modelMapper.map(
                offerServiceModel, Offer.class
        );

        offer.setCondition(conditionService
                .getConditionByName(offerServiceModel.getCondition()));
    offerRepository.save(offer);

    }

    @Override
    public OfferHomeDto getOffersFotHomePage() {

        List<Offer> offers= offerRepository.findAll();

        List<MyOfferDto> myOffers = new ArrayList<>();
        List<BoughtOffersDto> boughtOffers = new ArrayList<>();
        List<OtherOffersDto>otherOffers = new ArrayList<>();

        for (Offer offer : offers){
            String loggedUsername = loggedUser.getUsername();

            if (offer.getBoughtBy() == null && offer.getCreatedBy()
                    .getUsername().equals(loggedUsername)){
                myOffers.add(new MyOfferDto(offer));
            }else  if (offer.getBoughtBy() != null && offer.getBoughtBy().getUsername()
                    .equals(loggedUsername)){
                boughtOffers.add(new BoughtOffersDto(offer));
            }else if (offer.getBoughtBy()==null){
                otherOffers.add(new OtherOffersDto(offer));
            }
        }

        return new OfferHomeDto(myOffers,boughtOffers,otherOffers);
    }

    @Override
    public List<OfferViewModel> getOffersByCondition(ConditionName conditionName) {
        return offerRepository.findAllByConditionName(conditionName)
                .stream()
                .map(o ->modelMapper.map(o, OfferViewModel.class)
                        ).collect(Collectors.toList());
    }

    @Override
    public void removeAllOffers() {
        offerRepository.deleteAll();
    }

    @Override
    public void buy(UUID id) {

        Optional<Offer> optionalOffer = offerRepository.findById(id);

        if (optionalOffer.isPresent()){
            User user = userRepository.findByUsername(loggedUser.getUsername());
            Offer offer = optionalOffer.get();

            offer.setBoughtBy(user);


            offerRepository.save(offer);



        }

    }


}
