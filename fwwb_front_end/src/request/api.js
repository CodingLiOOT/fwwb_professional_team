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
