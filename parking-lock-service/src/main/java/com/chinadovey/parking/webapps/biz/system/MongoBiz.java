package com.chinadovey.parking.webapps.biz.system;

public interface MongoBiz {

	/**
	 * 备份 将mongoDb的数据备份到mysql
	 * 一条一条的更新，所以数据超慢
	 * @return
	 */
	int backupMongodbToMysql();

	/**
	 * 同步 将mongoDb的数据与mysql同步
	 * @return
	 * @throws ClassNotFoundException 
	 */
	int syncMongodbToMysql() throws ClassNotFoundException;

	/**
	 * 同步 将mysql的数据与mongoDb同步
	 * 会先删在添加 ，所以数据有几率丢失，怪我咯
	 * @return
	 */
	int syncMysqlToMongodb();

	void backupCarChargeRecordMongodbToMysql(Integer parkId);

	void backupParkMongodbToMysql();
	
}
