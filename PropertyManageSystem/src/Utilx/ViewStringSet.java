package Utilx;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Anno.FK;
import Anno.Hide;
import Anno.Label;

public class ViewStringSet {
	private Object datamodel;
	private Object viewmodel;

	public ViewStringSet(Object datamodel) {
		this.datamodel = datamodel;
	}

	public ViewStringSet() {
		// TODO Auto-generated constructor stub
	}

	public Object getDatamodel() {
		return datamodel;
	}

	public void setDatamodel(Object datamodel) {
		this.datamodel = datamodel;

	}

	// public Object Addset() throws IllegalArgumentException,
	// IllegalAccessException, NoSuchFieldException, SecurityException {
	//
	// String table = datamodel.getClass().getSimpleName();
	// table = table.substring(0, 1).toLowerCase()
	// + table.substring(1, table.length());
	// Field[] datafields = datamodel.getClass().getDeclaredFields();
	// Field[] viewfields = viewmodel.getClass().getDeclaredFields();
	// for (Field vfield : viewfields) {
	// for (Field dfield : datafields) {
	// vfield.setAccessible(true);
	// dfield.setAccessible(true);
	// if (dfield != null && vfield != null
	// && vfield.getName().equals(dfield.getName())) {
	// String fieldname = dfield.getName();
	// String label = (String) vfield.get(viewmodel);
	// vfield.set(viewmodel, "<label for='" + fieldname + "'>"
	// + label + "</label>" + "<input type='text' name='"
	// + table + "." + fieldname + "' id='"+fieldname+"' value='' /></br>\n");
	//
	// }
	// }
	// }
	//
	// return viewmodel;
	// }

	// public Object updateset() throws IllegalArgumentException,
	// IllegalAccessException, SecurityException, NoSuchFieldException {
	//
	// //反射获得字段集
	// Field[] datafields = datamodel.getClass().getDeclaredFields();
	// Field[] viewfields = viewmodel.getClass().getDeclaredFields();
	//
	// //获得类名
	// String table = datamodel.getClass().getSimpleName();
	// //首字母小写
	// table = table.substring(0, 1).toLowerCase()
	// + table.substring(1, table.length());
	//
	// for (Field vfield : viewfields) {
	// for (Field dfield : datafields) {
	// vfield.setAccessible(true);
	// dfield.setAccessible(true);
	// if (dfield != null && vfield != null
	// && vfield.getName().equals(dfield.getName())) {
	// //获得字段名
	// String fieldname = dfield.getName();
	// //获得字段值
	// String fieldvalue = String.valueOf(dfield.get(datamodel));
	//
	// String label = (String) vfield.get(viewmodel);
	// //组装成HTML语句并注入到Viewmodel中
	// vfield.set(viewmodel, "<label for='" + fieldname + "'>"
	// + label + "</label>" + "<input type='text' name='"
	// +table + "."+ fieldname + "' id='" + fieldname + "' value='"
	// + fieldvalue + "' /></br>\n");
	// }
	// }
	// }
	//
	// return viewmodel;
	// }

	public String Addset() {
		String str = "";
		Field[] datafields = datamodel.getClass().getDeclaredFields();
		// 获得类名
		String table = datamodel.getClass().getSimpleName();
		// 首字母小写
		table = table.substring(0, 1).toLowerCase()
				+ table.substring(1, table.length());

		for (Field dfield : datafields) {
			String label = "";

			// 获得字段名
			String fieldname = dfield.getName();
			// 获得类型名
			String type = dfield.getType().getSimpleName();
			// 类型转换
			type = typeConvert(type);

			// 获得字段值
			String fieldvalue;
			try {
				dfield.setAccessible(true);
				fieldvalue = String.valueOf(dfield.get(datamodel));
				// 获得label
				Annotation[] annotations = dfield.getAnnotations();
				Hide hide = dfield.getAnnotation(Hide.class);
				Label annotation = dfield.getAnnotation(Label.class);
				if (annotation != null) {
					label = annotation.value();
				}

				if (hide == null) {
					// 组装成HTML语句并注入到Viewmodel中
					str += "<div class='form-group row'><label for='"
							+ fieldname
							+ "' class='fieldname control-label col-sm-2 "
							+ fieldname + "' >" + label + ":" + "</label>"
							+ "<div class='col-md-4'><input type='" + type
							+ "' name='" + table + "." + fieldname
							+ "' class='form-control " + fieldname
							+ "' /></div></div><br/>\n";
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return str;
	}

	public String updateset() {
		String str = "";
		Field[] datafields = datamodel.getClass().getDeclaredFields();
		// 获得类名
		String table = datamodel.getClass().getSimpleName();
		// 首字母小写
		table = table.substring(0, 1).toLowerCase()
				+ table.substring(1, table.length());

		for (Field dfield : datafields) {
			String label = "";
			// 获得字段名
			String fieldname = dfield.getName();
			String type = dfield.getType().getSimpleName();
			// 类型转换
			type = typeConvert(type);
			// 获得字段值
			String fieldvalue;
			try {
				dfield.setAccessible(true);
				fieldvalue = String.valueOf(dfield.get(datamodel));
				Hide hide = dfield.getAnnotation(Hide.class);
				// 获得label
				Label annotation = dfield.getAnnotation(Label.class);
				if (annotation != null) {
					label = annotation.value();
				}
				if (hide == null) {
					// 组装成HTML语句并注入到Viewmodel中
					str += "<div class='form-group row'><label for='"
							+ fieldname
							+ "'class='fieldname control-label col-sm-2 "
							+ fieldname + "'>" + label + ":" + "</label>"
							+ "<div class='col-md-4'><input type='" + type
							+ "' name='" + table + "." + fieldname
							+ "' class='form-control " + fieldname
							+ "' value='" + fieldvalue
							+ "' /></div></div></br>\n";
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return str;
	}

	public String listSet(List<Object> list) throws IllegalArgumentException,
			IllegalAccessException, IntrospectionException {

		String ths = "";
		String trs = "";
		String lis = "";
		String tablename = "";
		String select = "";
		int i = 1;
		int p = 1;
		for (Object object : list) {
			String th = "";
			String td = "";
			String option = "";
			// String li="";

			// 获得类名
			tablename = object.getClass().getSimpleName();
			Field[] datafields = object.getClass().getDeclaredFields();
			String id = "";
			String idvalue = "";
			for (Field dfield : datafields) {
				String label = "";
				String fk = "";
				// 转化为公有属性
				dfield.setAccessible(true);

				// 获得label
				Label annotation1 = dfield.getAnnotation(Label.class);
				if (annotation1 != null) {
					label = annotation1.value();
				}

				String fieldname = dfield.getName();
				String fieldvalue = String.valueOf(dfield.get(object));

				if ("null".equalsIgnoreCase(fieldvalue)) {
					fieldvalue = "";
				}

				// 获得字段对应的get方法
				PropertyDescriptor pd = new PropertyDescriptor(
						dfield.getName(), object.getClass());
				Method getMethod = pd.getReadMethod();

				// 获得Get方法注解
				Annotation[] annotations = getMethod.getDeclaredAnnotations();

				for (Annotation annotation : annotations) {
					// 判断是不是主键
					if ("Id".equalsIgnoreCase(annotation.annotationType()
							.getSimpleName())) {
						// 获得主键名和值
						id = fieldname;
						idvalue = fieldvalue;
					}
				}

				FK fkann = dfield.getAnnotation(FK.class);
				if (fkann != null) {
					fk = fkann.value();
					td += "<td class='"
							+ fieldname
							+ "'><a href='"
							+ fk
							+ "Assign!findbyid?"
							+ fieldname
							+ "="
							+ fieldvalue
							+ "' target='_blank'  onclick=\"window.open(this.href,'_blank', 'scrollbars=0,resizable=0,width=600,height=300;');return false;\">"
							+ fieldvalue + "</td>\n";
					// 拼接th
					th += "<th class='" + fieldname + "'>" + label + "</th>\n";
					option += "<option value='" + fieldname + "'>" + label
							+ "</option>";
				} else {
					// 拼接td
					td += "<td class='" + fieldname + "'>" + fieldvalue
							+ "</td>\n";
					// 拼接th
					th += "<th class='" + fieldname + "'>" + label + "</th>\n";

					option += "<option class='option' id='" + fieldname
							+ "' value='" + fieldname + "'>" + label
							+ "</option>";
				}

			}
			// 首字母小写
			String tablenamel = tablename.substring(0, 1).toLowerCase()
					+ tablename.substring(1, tablename.length());
			// 增加修改和删除
			td = "<tr class='" + p + "'>" + td
					+ "<td><a class='edit glyphicon glyphicon-cog' id='" + id
					+ idvalue + "'" + " href='" + tablenamel + "Assign!edit?"
					+ id + "=" + idvalue + "'></a></td>\n"
					+ "<td><a class='delete glyphicon glyphicon-trash' id='"
					+ id + idvalue + "'" + " hs='" + tablenamel
					+ "Action!delete?" + id + "=" + idvalue
					+ "' ></a></td></tr>\n";
			th = "<thead><tr>" + th + "<th colspan='2'>操作</th></tr></thead>";
			if (i == 5) {
				lis += "<li><a href='javasript:retrun false' class='page'   ps='"
						+ p + "'>" + p + "</a></li>";
				p++;
				i = 0;
			}

			trs += td;
			ths = th;

			select = "<select class='' name='propertyName' id='propertyName'>" + option + "</select>";
			i++;
		}

		String lists = select + "<input type='text' name='value' id='value' /> "
				+ "<input  type='button' class='btn btn-primary'  id='submitlike' onclick='submitlike()' value='查询' />"
				+ "<hr/><div id='tables' class='table-responsive' ><table class='table table-striped table-hover table-bordered " + tablename + "'>\n" + ths
				+ "<tbody>" + trs + "</tbody>" + "</table>";

		String pagepick = "<nav>" + "<ul class=\"pagination\">" + "<li>"
				+ "<a  class='page' aria-label=\"Previous\">"
				+ "<span aria-hidden=\"true\">&laquo;</span>" + "</a>"
				+ "</li>" + lis + "<li>"
				+ "<a  class='page' aria-label=\"Next\">"
				+ "<span aria-hidden=\"true\">&raquo;</span>" + "</a>"
				+ "</li>" + "</ul>" + "</nav></div>";

		lists += pagepick;

		return lists;
	}

	public String likelistSet(List<Object> list)
			throws IllegalArgumentException, IllegalAccessException,
			IntrospectionException {

		String ths = "";
		String trs = "";
		String lis = "";
		String tablename = "";
		int i = 1;
		int p = 1;
		if(list.size()>0){
		for (Object object : list) {
			String th = "";
			String td = "";
			// String li="";

			// 获得类名
			tablename = object.getClass().getSimpleName();
			Field[] datafields = object.getClass().getDeclaredFields();
			String id = "";
			String idvalue = "";
			for (Field dfield : datafields) {
				String label = "";
				String fk = "";
				// 转化为公有属性
				dfield.setAccessible(true);

				// 获得label
				Label annotation1 = dfield.getAnnotation(Label.class);
				if (annotation1 != null) {
					label = annotation1.value();
				}

				String fieldname = dfield.getName();
				String fieldvalue = String.valueOf(dfield.get(object));

				if ("null".equalsIgnoreCase(fieldvalue)) {
					fieldvalue = "";
				}

				// 获得字段对应的get方法
				PropertyDescriptor pd = new PropertyDescriptor(
						dfield.getName(), object.getClass());
				Method getMethod = pd.getReadMethod();

				// 获得Get方法注解
				Annotation[] annotations = getMethod.getDeclaredAnnotations();

				for (Annotation annotation : annotations) {
					// 判断是不是主键
					if ("Id".equalsIgnoreCase(annotation.annotationType()
							.getSimpleName())) {
						// 获得主键名和值
						id = fieldname;
						idvalue = fieldvalue;
					}
				}

				FK fkann = dfield.getAnnotation(FK.class);
				if (fkann != null) {
					fk = fkann.value();
					td += "<td class='"
							+ fieldname
							+ "'><a href='"
							+ fk
							+ "Assign!findbyid?"
							+ fieldname
							+ "="
							+ fieldvalue
							+ "' target='_blank'  onclick=\"window.open(this.href,'_blank', 'scrollbars=0,resizable=0,width=600,height=300;');return false;\">"
							+ fieldvalue + "</td>\n";
					// 拼接th
					th += "<th class='" + fieldname + "'>" + label + "</th>\n";
				} else {
					// 拼接td
					td += "<td class='" + fieldname + "'>" + fieldvalue
							+ "</td>\n";
					// 拼接th
					th += "<th class='" + fieldname + "'>" + label + "</th>\n";
				}

			}
			// 首字母小写
			String tablenamel = tablename.substring(0, 1).toLowerCase()
					+ tablename.substring(1, tablename.length());
			// 增加修改和删除
			td = "<tr class='" + p + "'>" + td
					+ "<td><a class='edit glyphicon glyphicon-cog' id='" + id
					+ idvalue + "'" + " href='" + tablenamel + "Assign!edit?"
					+ id + "=" + idvalue + "'></a></td>\n"
					+ "<td><a class='delete glyphicon glyphicon-trash' id='"
					+ id + idvalue + "'" + " hs='" + tablenamel
					+ "Action!delete?" + id + "=" + idvalue
					+ "' ></a></td></tr>\n";
			th = "<thead><tr>" + th + "<th colspan='2'>操作</th></tr></thead>";
			if (i == 5) {
				lis += "<li><a href='javasript:retrun false' class='page'   ps='"
						+ p + "'>" + p + "</a></li>";
				p++;
				i = 0;
			}

			trs += td;
			ths = th;
			i++;
		}


		String lists = "<hr/><div id='tables' class='table-responsive'><table class='table table-striped table-hover table-bordered " + tablename + "'>\n" + ths
				+ "<tbody>" + trs + "</tbody>" + "</table>";

		String pagepick = "<nav>" + "<ul class=\"pagination\">" + "<li>"
				+ "<a  class='page' aria-label=\"Previous\">"
				+ "<span aria-hidden=\"true\">&laquo;</span>" + "</a>"
				+ "</li>" + lis + "<li>"
				+ "<a  class='page' aria-label=\"Next\">"
				+ "<span aria-hidden=\"true\">&raquo;</span>" + "</a>"
				+ "</li>" + "</ul>" + "</nav></div>";

			lists += pagepick;
			return lists;
		}
		else{
			return "<hr/><div style='color:#000000;'>根据当前字段和值没有查询到数据...</div>";
			
		}

		
	}

	public String typeConvert(String typename) {

		switch (typename) {
		case "Integer":
			return "number";
		case "Timestamp":
			return "date";
		default:
			return "text";
		}
	}

	// public String StringConvert(String match,String fieldname,String
	// fieldvalue,String label,String table){
	//
	// switch (match) {
	// case "fieldname":return fieldname;
	// case "fieldvalue":return fieldvalue;
	// case "label":return label;
	// case "table":return table;
	// default:return "";
	// }
	//
	// }
	// public String replace(String str,String regex){
	//
	// Matcher m = Pattern.compile(regex).matcher(str);
	// int i=0;
	// while(m.find(i)){
	// StringConvert(match, fieldname, fieldvalue, label, table);
	// }
	//
	// return "";
	// }

}
