package com.apple.flagchecker.service;

import com.apple.flagchecker.domain.Request;
import com.apple.flagchecker.entity.Continent;
import java.util.List;

public interface FlagService {

  List<Continent> getFlags(Request request);

}
