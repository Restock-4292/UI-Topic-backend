package com.restock.platform.profiles.application.internal.commandservices;

import com.restock.platform.profiles.domain.model.commands.CreateBusinessCommand;
import com.restock.platform.profiles.domain.model.commands.DeleteBusinessCommand;
import com.restock.platform.profiles.domain.model.commands.UpdateBusinessCommand;
import com.restock.platform.profiles.domain.model.entities.Business;
import com.restock.platform.profiles.infrastructure.persistence.jpa.repositories.BusinessRepository;
import com.restock.platform.profiles.infrastructure.persistence.jpa.repositories.BusinessCategoryRepository;
import com.restock.platform.profiles.domain.services.BusinessCommandService;
import com.restock.platform.shared.domain.exceptions.InvalidCredentialsException;
import com.restock.platform.shared.domain.repositories.UnitOfWork;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Business Command Service Implementation
 */
@Service
public class BusinessCommandServiceImpl implements BusinessCommandService {

    private final BusinessRepository businessRepository;
    private final BusinessCategoryRepository categoryRepository;
    private final UnitOfWork unitOfWork;

    /**
     * Constructor
     *
     * @param businessRepository Business repository
     * @param categoryRepository Category repository
     * @param unitOfWork         Unit of work
     */
    public BusinessCommandServiceImpl(BusinessRepository businessRepository,
                                      BusinessCategoryRepository categoryRepository,
                                      UnitOfWork unitOfWork) {
        this.businessRepository = businessRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfWork = unitOfWork;
    }

    @Override
    public Optional<Business> handle(CreateBusinessCommand command) {
        var business = new Business(command);
        businessRepository.save(business);
        unitOfWork.commit();
        return Optional.of(business);
    }

    @Override
    public void handle(UpdateBusinessCommand command) {
        var business = businessRepository.findById(command.businessId())
                .orElseThrow(() -> new InvalidCredentialsException("Business not found"));

        // Validate categories
        List<String> validCategoryNames = categoryRepository.findAll()
                .stream()
                .map(c -> c.getName().toLowerCase().trim())
                .toList();

        Set<String> inputCategories = Set.of(command.categories()
                        .split(",")).stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        List<String> invalid = inputCategories.stream()
                .filter(c -> !validCategoryNames.contains(c))
                .toList();

        if (!invalid.isEmpty()) {
            throw new InvalidCredentialsException("Invalid categories: " + String.join(", ", invalid));
        }

        business.update(
                command.name(),
                command.email(),
                command.phone(),
                command.address(),
                command.categories()
        );

        businessRepository.save(business);
        unitOfWork.commit();
    }

    @Override
    public void handle(DeleteBusinessCommand command) {
        var business = businessRepository.findById(command.businessId())
                .orElseThrow(() -> new InvalidCredentialsException("Business not found"));

        businessRepository.delete(business);
        unitOfWork.commit();
    }
}
