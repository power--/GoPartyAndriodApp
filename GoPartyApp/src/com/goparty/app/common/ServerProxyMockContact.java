package com.goparty.app.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ServerProxyMockContact
{
	private int i = 0;
	private List<Map<String, Object>> list;
	
	public void sendRequest(final ServerListener listener)
	{	
		final int num = i;
		if(i < 5)
		{
			Thread thread = new Thread() 
			{
				public void run() 
				{
					try
					{
						this.sleep(1000);
					}
					catch(Exception e)
					{
						
					}
					
					list = createData(num);
					if(i == 4)
					{
						listener.serverDataArrived(list, true);
					}
					else
					{
						listener.serverDataArrived(list, false);
					}
					
				}
			};
			thread.start();
			i++;
		}
		
		
	}
	
	private List<Map<String, Object>> createData(int i)
	{
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		if(i == 0)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("faceUrl", "1.png");
			map.put("name", "HelloWorld");
			map.put("signature", "Welcome to the World!");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("faceUrl", "2.png");
			map.put("name", "Android");
			map.put("signature", "Hi!");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "Apple");
			map.put("signature", "Jobs!");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "Windows");
			map.put("signature", "Micro Micro Micro Mobile!");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "XXX");
			map.put("signature", "YYY~~");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "MBP");
			map.put("signature", "Retina~~");
			list.add(map);
		}
		
		if(i == 1)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("faceUrl", "1.png");
			map.put("name", "i1_0");
			map.put("signature", "Welcome to the World!");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("faceUrl", "2.png");
			map.put("name", "i1_1");
			map.put("signature", "Hi!");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i1_2");
			map.put("signature", "Jobs!");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i1_3");
			map.put("signature", "Micro Micro Micro Mobile!");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i1_4");
			map.put("signature", "YYY~~");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i1_5");
			map.put("signature", "Retina~~");
			list.add(map);
		}
		
		if(i == 2)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("faceUrl", "1.png");
			map.put("name", "i2_0");
			map.put("signature", "Welcome to the World!");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("faceUrl", "2.png");
			map.put("name", "i2_1");
			map.put("signature", "Hi!");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i2_2");
			map.put("signature", "Jobs!");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i2_3");
			map.put("signature", "Micro Micro Micro Mobile!");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i2_4");
			map.put("signature", "YYY~~");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i2_5");
			map.put("signature", "Retina~~");
			list.add(map);
		}
		
		if(i == 3)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("faceUrl", "1.png");
			map.put("name", "i3_0");
			map.put("signature", "Welcome to the World!");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("faceUrl", "2.png");
			map.put("name", "i3_1");
			map.put("signature", "Hi!");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i3_2");
			map.put("signature", "Jobs!");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i3_3");
			map.put("signature", "Micro Micro Micro Mobile!");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i3_4");
			map.put("signature", "YYY~~");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i3_5");
			map.put("signature", "Retina~~");
			list.add(map);
		}
		
		if(i == 4)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("faceUrl", "1.png");
			map.put("name", "i4_0");
			map.put("signature", "Welcome to the World!");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("faceUrl", "2.png");
			map.put("name", "i4_1");
			map.put("signature", "Hi!");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i4_2");
			map.put("signature", "Jobs!");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i4_3");
			map.put("signature", "Micro Micro Micro Mobile!");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i4_4");
			map.put("signature", "YYY~~");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("faceUrl", "3.png");
			map.put("name", "i4_5");
			map.put("signature", "Retina~~");
			list.add(map);
		}	
		return list;
	}
}