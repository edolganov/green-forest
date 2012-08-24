package com.green.forest.api.test.interceptor;

import com.green.forest.api.Interceptor;
import com.green.forest.api.annotation.Mapping;
import com.green.forest.api.test.action.StringAction;

@Mapping(StringAction.class)
public class StringReverse extends Interceptor<StringAction>{

}
