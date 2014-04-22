package com.goparty.app.common;

import java.util.List;

public interface ServerListener<T> 
{
	void serverDataArrived(List<T> list, boolean isEnd);
}
