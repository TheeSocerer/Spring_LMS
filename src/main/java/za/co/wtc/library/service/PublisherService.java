package za.co.wtc.library.service;

import za.co.wtc.library.model.Publisher;

public interface PublisherService {
    Publisher findByIdISNI(String ISNI);
}
