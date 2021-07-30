server.xml 에 추가함
<Context docBase="springBoard" path="/springBoard" reloadable="true" source="org.eclipse.jst.jee.server:springBoard">
					<ResourceLink global="jdbc/Oracle" name="jdbc/Oracle" type="javax.sql.DataSource"/>
				</Context>
