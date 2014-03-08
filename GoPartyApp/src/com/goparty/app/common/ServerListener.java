package com.goparty.app.common;

import java.util.List;

public interface ServerListener 
{
	void serverDataArrived(List list, boolean isEnd);
}
