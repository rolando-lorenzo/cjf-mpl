// Merges the provided configuration with the pipeline config

import com.ceva.cjflib.MPLManager

def call(cfg) {
  MPLManager.instance.configMerge(cfg)
}
