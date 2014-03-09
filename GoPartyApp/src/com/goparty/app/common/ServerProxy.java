package com.goparty.app.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ServerProxy
{
	private int i = 0;
	private List<Map<String, Object>> list;
//	private Object obj;
	
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

			map.put("name", "Mo-Mo");
			map.put("price", "禄脌忙藰拢鈭�脩6147");
			map.put("addr", "陋楼鈭Ｂ垜 禄鈥櫬犖┾棅鈥樏匪�");
			map.put("distance", "5.8km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "鈮ヂ捤浡�");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "鈮ツ甭�");
			map.put("price", "禄脌忙藰拢鈭�脩6285");
			map.put("addr", "鈭伮増 禄鈥櫬泵β∨撀棵�");
			map.put("distance", "890m");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "鈮ヂ捤浡�z");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "惟鈮犅憋瑐鈮ヂ曗�艗鈼娾�惟鈥︹�禄麓鈥濃�拢庐惟铮柯∶嶁垈麓卢鈭懧得嵚Ｂ�");
			map.put("price", "禄脌忙藰拢鈭�脩660");
			map.put("addr", "禄脌鈭毭捪��鈮ヂ�楼庐鈮っ�");
			map.put("distance", "8.1km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", true);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "鈮ヂ捤浡�");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "卢脙鈮っ嬧墹脮脙赂");
			map.put("price", "禄脌忙藰拢鈭�脩660");
			map.put("addr", "卢鈮モ�鈭徬�粹�鈭�鈭衡垶脭鈮っ�");
			map.put("distance", "10km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "鈥櫬⒙甭甭�");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "鈥ε撆撁屸墹脮脙赂");
			map.put("price", "禄脌忙藰拢鈭�脩669");
			map.put("addr", "鈮ヂ犈嘎垜 鈮っ嬧墹脮脙赂");
			map.put("distance", "5.0km");
			map.put("tuan", true);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "鈥櫬⒙甭甭�");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "脮谋鈭偮堵烩�卤忙隆艙驴脤脙藱鈭灻傗�鈥�");
			map.put("price", "禄脌忙藰拢鈭�脩6240");
			map.put("addr", "禄脌鈭毭捪��鈮ヂ�禄鈥櫬泵β∨撀棵�");
			map.put("distance", "7.4km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "鈭喢封垈麓鈥撀�");
			list.add(map);
		}
		
		if(i == 1)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("name", "脕忙酶脮");
			map.put("price", "禄脌忙藰拢鈭�脩6136");
			map.put("addr", "艗藴聽惟脙铮柯碘�");
			map.put("distance", "2.0km");
			map.put("tuan", true);
			map.put("promo", false);
			map.put("card", true);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "鈥撁徛�芦炉");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "忙鈭喢暶�");
			map.put("price", "禄脌忙藰拢鈭�脩6324");
			map.put("addr", "隆藱鈭灻柯得柯�禄鈥櫬泵β∨撀棵�");
			map.put("distance", "4.0km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "芦鈥♀垎梅芦炉");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "艙茠鈭傗�艗藴鈮っ暶兟�");
			map.put("price", "禄脌忙藰拢鈭�脩676");
			map.put("addr", "鈥撁徛衡�陋鈥�艗藴聽惟潞脷鈮っ�");
			map.put("distance", "4.4km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "脌鈥ξ┾墵芦炉");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "脮鈥氣垎鈮埪衡�");
			map.put("price", "禄脌忙藰拢鈭�脩656");
			map.put("addr", "陋铮库墺碌鈥櫭�鈭衡垶脭鈮っ�");
			map.put("distance", "8.0km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "卤露鈥ξ┞�");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "惟鈮犅憋瑐鈮ヂ曗�艗鈼娾�惟鈥︹�禄麓鈥濃�拢庐茠艙忙漏艗藴卢鈭懧得嵚Ｂ�");
			map.put("price", "禄脌忙藰拢鈭�脩663");
			map.put("addr", "茠艙忙漏艗藴卢鈭�楼庐鈮っ�");
			map.put("distance", "6.5km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", true);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "脌鈥ξ┾墵芦炉");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "鈭炁糕埆艙鈭徛ｂ埆拢艙聽鈥︹増聽鈮モ棅鈥孤�拢庐禄鈥欌�卢蟺鈥毬得嵚Ｂ�");
			map.put("price", "禄脌忙藰拢鈭�脩6246");
			map.put("addr", "楼脷鈭喢仿増 鈼娾�梅藱鈮っ�");
			map.put("distance", "6.6km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "卤露鈥ξ┞�");
			list.add(map);
		}
		
		if(i == 2)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("name", "惟铮柯喡扁劉拢庐鈥斺�鈭炩墹艗藴卢鈭懧得嵚Ｂ�");
			map.put("price", "禄脌忙藰拢鈭�脩6224");
			map.put("addr", "鈭偯樑捗斺�鈭�鈭伮増陋藱鈮ヂ�鈼娾�梅藱鈮っ�");
			map.put("distance", "2.9km");
			map.put("tuan", true);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "鈥撁徛�芦炉");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "鈮ぢ衡垙脣鈭偮疵β┞Ｂ埆脕酶鈦劼∷櫭访嗏垰艗碌脥拢漏");
			map.put("price", "禄脌忙藰拢鈭�脩627");
			map.put("addr", "卢鈮モ�鈭徬�粹�鈭�艗藴聽惟脙铮柯碘�");
			map.put("distance", "6.8km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "鈭伱糕亜芦炉");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "BLACK MAGIC CHOCOLATE");
			map.put("price", "禄脌忙藰拢鈭�脩648");
			map.put("addr", "楼脷鈭喢仿増 艗藴聽惟潞脷鈮っ�");
			map.put("distance", "6.8km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "芦鈥♀垎梅芦炉");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "70鈭涒垜蟺鈭炩�");
			map.put("price", "禄脌忙藰拢鈭�脩662");
			map.put("addr", "鈮ヂ犈嘎垜 卤忙鈭灻斘┾墵鈥欌�鈮っ�");
			map.put("distance", "5.2km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "鈥斆撯垎梅芦炉");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "艙鈮も垈鈥∨掆�蟺藱潞聽鈭Ｅ撀犅垎鈭嗏垜");
			map.put("price", "禄脌忙藰拢鈭�脩6227");
			map.put("addr", "卢惟潞鈥溾棅脧 鈼娾�梅藱鈮っ�");
			map.put("distance", "10km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "鈥撁徛�芦炉");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "鈥溍喡棵斉撈捖克嗏�卢惟脝鈮っ�Ｂ�藳脮庐碌脥拢漏拢漏");
			map.put("price", "禄脌忙藰拢鈭�脩669");
			map.put("addr", "艗脗惟芦鈮ヂ�楼脹鈥斆熉�鈥撀┟�芦脗鈥櫭�");
			map.put("distance", "6.6km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "鈥撁徛�芦炉");
			list.add(map);
		}
		
		if(i == 3)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("name", "艗鈥�");
			map.put("price", "禄脌忙藰拢鈭�脩667");
			map.put("addr", "忙鈮も垶鈮っ��酶脽鈭懧幻兟�");
			map.put("distance", "5.4km");
			map.put("tuan", true);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "鈮ヂ捤浡�");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "脙漏脗藱鈭徝浡Ｂ埆脕酶鈦劼∷櫭访嗏垰艗碌脥拢漏");
			map.put("price", "禄脌忙藰拢鈭�脩671");
			map.put("addr", "卢鈮モ�鈭徬�粹�鈭�鈭偮雌捙撯�芦鈮っ�");
			map.put("distance", "10km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "芦鈥♀垎梅芦炉");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "禄藵卤露梅鈥♀垎脙");
			map.put("price", "禄脌忙藰拢鈭�脩629");
			map.put("addr", "禄脌鈭毭捪��鈮ヂ�酶脧鈮っ暵好氣墹脮");
			map.put("distance", "8.1km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "鈥斆撯垎梅芦炉");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "鈥撀扳�惟禄鈥櫬泵β∨撀棵�");
			map.put("price", "禄脌忙藰拢鈭�脩6183");
			map.put("addr", "鈥撀兠徛得�禄鈥櫬泵β∨撀棵�");
			map.put("distance", "7.4km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "鈭喢封垈麓鈥撀�");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "茠鈩⑩垈藲鈭偱糕増拢鈮堚増鈭懧�");
			map.put("price", "禄脌忙藰拢鈭�脩6602");
			map.put("addr", "卢惟潞鈥溾棅脧 鈮埪ｂ増鈮�");
			map.put("distance", "10km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 05);
			map.put("area", "鈭喢封垈麓鈥撀�");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "鈥撀扳�掳陋庐鈥樷垶");
			map.put("price", "禄脌忙藰拢鈭�脩669");
			map.put("addr", "鈥撁徛衡�陋鈥�酶脽鈭懧�");
			map.put("distance", "3.7km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "鈭喢封垈麓鈥撀�");
			list.add(map);
		}
		
		if(i == 4)
		{
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("name", "鈭灺⒚︹垰");
			map.put("price", "禄脌忙藰拢鈭�脩6103");
			map.put("addr", "梅鈥撯�惟蟺麓鈥樷垶 禄鈥櫬泵β∨撀棵�");
			map.put("distance", "2.4km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "鈥撁徛�芦炉");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "鈥溌ㄅ撯�脙脧脙鈭�");
			map.put("price", "禄脌忙藰拢鈭�脩6179");
			map.put("addr", "忙鈮も垶鈮っ��脙漏蟺藵鈮っ�");
			map.put("distance", "5.1km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "鈥撁徛�芦炉");
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("name", "鈥澛┾垎铿偮幻�衡�");
			map.put("price", "禄脌忙藰拢鈭�脩6137");
			map.put("addr", "忙鈮も垶鈮っ��楼楼鈥溾�鈮っ�");
			map.put("distance", "7.1km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "鈥撁徛�芦炉");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "艙鈥γ访��藰拢庐鈭戔亜鈥斆櫬垜碌脥拢漏");
			map.put("price", "禄脌忙藰拢鈭�脩6457");
			map.put("addr", "鈥溍櫬棵封�脽鈥樷埆 禄鈥櫬泵︹�鈥櫭该�");
			map.put("distance", "5.1km");
			map.put("tuan", false);
			map.put("promo", false);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 50);
			map.put("area", "鈭伱糕亜芦炉");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "鈼娒樷�楼");
			map.put("price", "禄脌忙藰拢鈭�脩6224");
			map.put("addr", "鈥撀兠徛得�艗藴鈭炩�鈥斅库墹脌");
			map.put("distance", "7.4km");
			map.put("tuan", true);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 40);
			map.put("area", "鈭伱糕亜芦炉");
			list.add(map);
			
			map = new HashMap<String, Object>();
			map.put("name", "脣梅鈼娾�拢庐卢惟潞鈥溾棅脧碌脥拢漏");
			map.put("price", "禄脌忙藰拢鈭�脩6206");
			map.put("addr", "卢惟潞鈥溾棅脧 禄鈥櫬泵β∨撀棵�");
			map.put("distance", "10km");
			map.put("tuan", false);
			map.put("promo", true);
			map.put("card", false);
			map.put("checkin", false);
			map.put("star", 45);
			map.put("area", "鈭伱糕亜芦炉");
			list.add(map);
		}	
		return list;
	}
}