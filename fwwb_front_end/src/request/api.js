import {get,post} from './http'


export default class api {

  /**
   * 登录接口，发送用户信息到后端查验用户信息
   * @param {Object} p [发送的参数:登录用户名和密码]
   * @returns {Promise<unknown>} [返回登录用户的信息]
   */
  static p_Login=p=>post('/user/login',p);

  /**
   * 注册接口，发送用户信息到后端注册
   * @param {Object} p [发送的参数：注册用户名、密码、邮箱]
   * @returns {Promise<unknown>} [返回注册成功的msg]
   */
  static p_Register=p=>post('/user/register',p);

  /**
   * 某一站点信息接口，发送线名，站名，开始时间，结束时间，时间粒度到后端查询
   * @param {Object} p [发送的参数：线名、站名、起始时间、结束时间、时间粒度]
   * @returns {Promise<unknown>} [返回进站人数、出站人数、年龄结构]
   */
  static p_Station=p=>post('/station/getPassengerByTime',p);

  /**
   * 某一线路信息接口，发送线名，开始时间，结束时间，时间粒度到后端查询
   * @param {Object} p [发送的参数：线名、起始时间、结束时间、时间粒度]
   * @returns {Promise<unknown>} [返回进站人数、出站人数、年龄结构]
   */
  static p_Line=p=>post('/line/getPassengerByTime',p);

  /**
   * 传起始时间和结束时间给后端，返回该时间段内所有线路的进线和出线人数
   * @param {Object} p [发送的参数：起始时间、结束时间]
   * @returns {Promise<unknown>} [返回所有线路出线和进线人数]
   */
  static p_LineRatio=p=>post('/line/getRatio',p);

  /**
   * 获取所有线路、站点信息以初始化级联选择器
   * @param null
   * @returns {Promise<unknown>} [返回所有线路名称、线路下所有站点名称]
   */
  static g_getAllStationInfo=()=>get('/station/getLineStationInfo',null);

  /**
   * 获取总览的年龄结构和线路占比
   * @param {Object} p [发送的参数：时间]
   * @returns {Promise<unknown>} [返回当天各年龄段人数]
   */
  static p_getAgeLine=()=>post('/overview/getAgeLineRatio',p);

  /**
   * 获取工作日和周末，某条线路或者总览的客流量
   * @param {Object} p [发送的参数：线路号（或者0，为总览）]
   * @returns {Promise<unknown>} [返回工作日、周末和节假日，站号或者线号，进站人数，出战人数]
   */
  static p_wwhData=p=>post('/line/getPassengerWeek',p);


  /**
   * 测试接口，测试token拦截
   * @param null
   * @returns {Promise<unknown>} [返回全部用户信息]
   */
  static g_getAllUser=()=>get('/user/getAllUser',null);

  /**
   * 假接口，仅展示带参数的get写法即后端获取参数的方式
   * @param p [发送的参数：发送任意数字]
   * @returns {Promise<unknown>} [返回请求的数字]
   */
  static g_getAll=p=>get('/sss',p);
}
