import pymysql


def load_csv(csv_file_path, table_name, database='fwwb_dataset'):
    # 打开csv文件
    file = open(csv_file_path, 'r', encoding='utf-8')
    # 读取csv文件第一行字段名，创建表
    reader = file.readline()
    b = reader.split(',')
    column = ''
    for a in b:
        column = column + a + ' varchar(255),'
    column = column[:-1]
    # 编写sql，create_sql负责创建表，data_sql负责导入数据
    create_sql = 'create table if not exists ' + table_name + ' ' + '(' + column + ')' + ' DEFAULT CHARSET=utf8'
    data_sql = "LOAD DATA LOCAL INFILE '%s' INTO TABLE %s FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\r\\n' IGNORE 1 LINES" % (
        csv_file_path, table_name)

    # 使用数据库
    cur.execute('use %s' % database)
    # 设置编码格式
    cur.execute('SET NAMES utf8;')
    cur.execute('SET character_set_connection=utf8;')
    # 执行create_sql，创建表
    cur.execute(create_sql)
    # 执行data_sql，导入数据
    cur.execute(data_sql)
    conn.commit()


if __name__ == '__main__':
    config = {
        'host': '39.107.205.151',
        'port': 3306,
        'user': 'root',
        'passwd': '123456',
        'local_infile': 1
    }
    conn = pymysql.connect(**config)
    cur = conn.cursor()
    load_csv('usersdata.csv', 'users')
    load_csv('tripsdata.csv', 'trips')
    load_csv('stationdata.csv', 'stations')
    load_csv('workdays2020data.csv', 'workdays')
    # 关闭连接
    conn.close()
    cur.close()
