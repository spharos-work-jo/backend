package com.workjo.pointapp.store.application;


import com.workjo.pointapp.store.vo.StoreGetOut;

import java.util.List;


public interface FavoriteStoreService {

	List<StoreGetOut> getFavoriteStoreListByUserUUIDString(String uuidString);

}
