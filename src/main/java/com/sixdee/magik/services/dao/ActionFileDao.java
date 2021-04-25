package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.ActionFileTO;


public interface ActionFileDao {
  public void saveFileDetails(List<ActionFileTO> actionFileTOs);
}
